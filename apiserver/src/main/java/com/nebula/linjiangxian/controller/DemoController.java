package com.nebula.linjiangxian.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nebula.linjiangxian.model.Author;
import com.nebula.linjiangxian.model.Poetry;
import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.Node;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.data.ValueWrapper;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;

@Controller
@RequestMapping("/api")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    private NebulaPool pool = new NebulaPool();
    private Session session = null;
    private String[] citys = new String[] {"石家庄市","唐山市", "秦皇岛市", "邯郸市","邢台市",
        "保定市","张家口市","承德市","沧州市","廊坊市","衡水市","太原市","大同市","阳泉市","长治市","晋城市",
        "朔州市","晋中市","运城市","忻州市","临汾市","吕梁市","呼和浩特市","包头市","乌海市","赤峰市","通辽市",
        "鄂尔多斯市","呼伦贝尔市","巴彦淖尔市","乌兰察布市","沈阳市","大连市","鞍山市","抚顺市","本溪市","丹东市",
        "锦州市","营口市","阜新市","辽阳市","盘锦市","铁岭市","朝阳市","葫芦岛市","长春市","吉林市","四平市","辽源市",
        "通化市","白山市","松原市","白城市","哈尔滨市","齐齐哈尔市","鸡西市","鹤岗市","双鸭山市","大庆市","伊春市",
        "佳木斯市","七台河市","牡丹江市","黑河市","绥化市","南京市","无锡市","徐州市","常州市","苏州市","南通市",
        "连云港市","淮安市","盐城市","扬州市","镇江市","泰州市","宿迁市","杭州市","宁波市","温州市","嘉兴市","湖州市",
        "绍兴市","金华市","衢州市","舟山市","台州市","丽水市","合肥市","芜湖市","蚌埠市","淮南市","马鞍山市","淮北市",
        "铜陵市","安庆市","黄山市","阜阳市","宿州市","滁州市","六安市","宣城市","池州市","亳州市","福州市","厦门市",
        "莆田市","三明市","泉州市","漳州市","南平市","龙岩市","宁德市","南昌市","景德镇市","萍乡市","九江市","抚州市",
        "鹰潭市","赣州市","吉安市","宜春市","新余市","上饶市","济南市","青岛市","淄博市","枣庄市","东营市","烟台市",
        "潍坊市","济宁市","泰安市","威海市","日照市","临沂市","德州市","聊城市","滨州市","菏泽市","郑州市","开封市",
        "洛阳市","平顶山市","安阳市","鹤壁市","新乡市","焦作市","濮阳市","许昌市","漯河市","三门峡市","南阳市","商丘市",
        "信阳市","周口市","驻马店市","武汉市","黄石市","十堰市","宜昌市","襄阳市","鄂州市","荆门市","孝感市","荆州市",
        "黄冈市","咸宁市","随州市","长沙市","株洲市","湘潭市","衡阳市","邵阳市","岳阳市","常德市","张家界市","益阳市",
        "郴州市","永州市","怀化市","娄底市","广州市","韶关市","深圳市","珠海市","汕头市","佛山市","江门市","湛江市",
        "茂名市","肇庆市","惠州市","梅州市","汕尾市","河源市","阳江市","清远市","东莞市","中山市","潮州市","揭阳市",
        "云浮市","南宁市","柳州市","桂林市","梧州市","北海市","崇左市","来宾市","贺州市","玉林市","百色市","河池市",
        "钦州市","防城港市","贵港市","海口市","三亚市","三沙市","儋州市","成都市","自贡市","攀枝花市","泸州市","德阳市",
        "绵阳市","广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市","广安市","达州市","雅安市","巴中市",
        "资阳市","贵阳市","六盘水市","遵义市","安顺市","毕节市","铜仁市","昆明市","曲靖市","玉溪市","保山市","昭通市",
        "丽江市","普洱市","临沧市","拉萨市","日喀则市","昌都市","林芝市","山南市","那曲市","西安市","铜川市","宝鸡市",
        "咸阳市","渭南市","延安市","汉中市","榆林市","安康市","商洛市","兰州市","嘉峪关市","金昌市","白银市","天水市",
        "武威市","张掖市","平凉市","酒泉市","庆阳市","定西市","陇南市","西宁市","海东市","银川市","石嘴山市","吴忠市",
        "固原市","中卫市","乌鲁木齐市","克拉玛依市","吐鲁番市","哈密市","北京市","天津市","上海市","重庆市"};

    public DemoController() {
        try {
            NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
            nebulaPoolConfig.setMaxConnSize(300);
            List<HostAddress> addresses = Arrays.asList(new HostAddress("host", 1111));
            pool.init(addresses, nebulaPoolConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/test")
    @ResponseBody
    public String testDemo() {
        return "Hello World!";
    }

    @RequestMapping("/match")
    @ResponseBody
    public List<JSONObject> getPoetryByID(@RequestParam(value="vid",
            required=true) String vid) {
        String query = "use poetry; MATCH (v)  WHERE id(v) == '"+ vid +"' " +
                       "RETURN v";

        log.info("This query is " + query);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        try {
            session = pool.getSession("root", "nebula-pass", false);
            ResultSet resp = session.execute(query);

            if (!resp.isSucceeded()) {
                log.error(String.format("Execute: `%s', failed: %s",
                                        query, resp.getErrorMessage()));
                System.exit(1);
            }

            jsonObjects = returnPoetryList(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.release();
        }
        return jsonObjects;
    }

    /**
     * Get author information by author name
     * @param name
     * @return
     */
    @RequestMapping ("/author")
    @ResponseBody
    public List<Author> getAuthorByName(@RequestParam(value="name",required=true) String name) {

        //this query is ok, but return nothing
//      String query = "use poetry;MATCH (v:author) \n" +
//                        "WHERE v.name == \" " + name + "\" \n" +
//                        "RETURN v;";

        //this query is ok ,return all values
        String query = "use poetry; MATCH (v:author {name: \""+ name +
                           "\"}) RETURN v";

        log.info("This query is " + query);
        //nGQL is ok ,but only return v.id
//        String query = "use poetry;LOOKUP ON\n" +
//                       "  author \n" +
//                       "WHERE  `author`.name == \"" + name + "\" ";

        List<Author> authors = new ArrayList<Author>();
        try {
            session = pool.getSession("root", "nebula-pass", false);
            ResultSet resp = session.execute(query);

            if (!resp.isSucceeded()) {
                log.error(String.format("Execute: `%s', failed: %s",
                                        query, resp.getErrorMessage()));
                System.exit(1);
            }

            authors = returnAuthors(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.release();
        }
        return authors;
    }

    /**
     * Get all poetry by author or city.
     * Only for demo show
     * @param query
     * @return
     */
    @RequestMapping ("/poetry")
    @ResponseBody
    public List<JSONObject> getPoetry(@RequestParam(value="query",
            required=true)  String query) {

        List<JSONObject> poetryList = new ArrayList<JSONObject>();

        //如果是城市,执行通过城市获取诗词的方式。城市仅限地级市
        if (query.contains("市") | Arrays.asList(citys).contains(query + "市")) {
            log.info("the param is: " + query + " get poetry by city");
            poetryList = this.getPoetryByCity(query);
        } else {
            log.info("the param is: " + query + " get poetry by author");
            poetryList = this.getPoetryByAuthor(query);
        }

        return poetryList;
    }

    /**
     * Get all poetry by author
     * @param author
     * @return
     */
    @RequestMapping ("/poetry/author")
    @ResponseBody
    public List<JSONObject> getPoetryByAuthor(@RequestParam(value="author",
            required=true)  String author) {

        List<JSONObject> poetryList = new ArrayList<JSONObject>();
        String query = "use poetry; MATCH (v1:author{name: \""+ author +"\"}) -[r]-> (v2)" +
                       "\n    RETURN v2";
        log.info("This query is " + query);
        try {

            session = pool.getSession("root", "nebula-pass", false);
            ResultSet resp = session.execute(query);

            if (!resp.isSucceeded()) {
                log.error(String.format("Execute: `%s', failed: %s",
                                        query, resp.getErrorMessage()));
                System.exit(1);
            }

            poetryList = returnPoetryList(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.release();
        }

        return poetryList;
    }

    /**
     * Get all poetry by city
     * @param city
     * @return
     */
    @RequestMapping ("/poetry/city")
    @ResponseBody
    public List<JSONObject> getPoetryByCity(@RequestParam(value="city",
            required=true)   String city) {

        if (!city.contains("市")) {
            System.out.println("the param is: " + city + " add 市 for query");
            city = city + "市";
        }

        List<JSONObject> poetryList = new ArrayList<JSONObject>();
        String query = "use poetry; MATCH (v1:city{name: \""+ city +"\"}) " +
                       "<-[r]- (v2)" +
                       "\n    RETURN v2";
        log.info("This query is " + query);
        try {

            session = pool.getSession("root", "nebula-pass", false);
            ResultSet resp = session.execute(query);

            if (!resp.isSucceeded()) {
                log.error(String.format("Execute: `%s', failed: %s",
                                        query, resp.getErrorMessage()));
                System.exit(1);
            }

//            printResult(resp);
            poetryList = returnPoetryList(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.release();
        }
        return poetryList;
    }

    /**
     * Get all poetry by username.
     * This poetry will contains the last name and first name .
     * @param username
     * @return
     */
    @RequestMapping ("/poetry/username")
    @ResponseBody
    public List<JSONObject> getPoetryByUsername(@RequestParam(value="username",
            required=true)   String username) {

        List<JSONObject> poetryList = new ArrayList<JSONObject>();

        char[] names = username.toCharArray();

        String[] props = new String[] {
                "author", "name", "content"
        };

        String query = "use poetry; " ;

        for (int i = 0; i< props.length; i++) {
            for(int j = 0; j< props.length; j++) {
                if (names.length == 2) {
                    query += "MATCH (v:poetry) where v." + props[i] + " " + "CONTAINS \"" + names[0] + "\" and ";
                    query += "v." + props[j] + " CONTAINS \"" + names[1] + "\" " + "RETURN v \n";
                    if (!(i == props.length -1 && j == props.length -1))
                        query += " UNION ALL ";
                }
                if (names.length == 3) {
                    for(int m = 0; m< props.length; m++) {
                        query += "MATCH (v:poetry) where v." + props[i] + " " + "CONTAINS \"" + names[0] + "\" and ";
                        query += "v." + props[j] + " CONTAINS \"" + names[1] + "\" and ";
                        query += "v." + props[m] + " CONTAINS \"" + names[2] + "\" " + "RETURN v \n";

                        if (!(i == props.length -1 && j == props.length -1 && m == props.length -1))
                            query += " UNION ALL ";
                    }

                }
            }
        }

        log.info("This query is " + query);

        try {

            session = pool.getSession("root", "nebula-pass", false);
            ResultSet resp = session.execute(query);

            if (!resp.isSucceeded()) {
                log.error(String.format("Execute: `%s', failed: %s",
                                        query, resp.getErrorMessage()));
                System.exit(1);
            }

            //            printResult(resp);
            poetryList = returnPoetryList(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.release();
        }
        return poetryList;
    }

    /**
     * print json result from nebula
     * @param resultSet
     * @throws UnsupportedEncodingException
     */
    private static void printResult(ResultSet resultSet) throws
            UnsupportedEncodingException {
        List<String> colNames = resultSet.keys();
        for (String name : colNames) {
            System.out.printf("%15s |", name);
        }
        System.out.println();
        for (int i = 0; i < resultSet.rowsSize(); i++) {
            ResultSet.Record record = resultSet.rowValues(i);
            for (ValueWrapper value : record.values()) {
                if (value.isLong()) {
                    System.out.printf("%15s |", value.asLong());
                }
                if (value.isBoolean()) {
                    System.out.printf("%15s |", value.asBoolean());
                }
                if (value.isDouble()) {
                    System.out.printf("%15s |", value.asDouble());
                }
                if (value.isString()) {
                    System.out.printf("%15s |", value.asString());
                }
                if (value.isTime()) {
                    System.out.printf("%15s |", value.asTime());
                }
                if (value.isDate()) {
                    System.out.printf("%15s |", value.asDate());
                }
                if (value.isDateTime()) {
                    System.out.printf("%15s |", value.asDateTime());
                }
                if (value.isVertex()) {
                    System.out.printf("%15s |", value.asNode());
                }
                if (value.isEdge()) {
                    System.out.printf("%15s |", value.asRelationship());
                }
                if (value.isPath()) {
                    System.out.printf("%15s |", value.asPath());
                }
                if (value.isList()) {
                    System.out.printf("%15s |", value.asList());
                }
                if (value.isSet()) {
                    System.out.printf("%15s |", value.asSet());
                }
                if (value.isMap()) {
                    System.out.printf("%15s |", value.asMap());
                }
            }
            System.out.println();
        }
    }

    /**
     * return Vertex json array
     * @param resultSet
     * @return
     * @throws UnsupportedEncodingException
     */
    private static JSONArray returnJson(ResultSet resultSet) throws
            UnsupportedEncodingException {

        List colNames = resultSet.keys();

        JSONArray json = new JSONArray();

        for (int i = 0; i < resultSet.rowsSize(); i++) {

            JSONObject obj = new JSONObject();
            ResultSet.Record record = resultSet.rowValues(i);

            int j=0;
            for (ValueWrapper value : record.values()) {
                String columnName = colNames.get(j).toString();
                j++;
                if (value.isLong()) {
                    obj.put(columnName,value.asLong());
                }
                if (value.isBoolean()) {
                    obj.put(columnName,value.asBoolean());
                }
                if (value.isDouble()) {
                    obj.put(columnName,value.asDouble());
                }
                if (value.isString()) {
                    obj.put(columnName,value.asString());
                }
                if (value.isTime()) {
                    obj.put(columnName,value.asTime());
                }
                if (value.isDate()) {
                    obj.put(columnName,value.asDate());
                }
                if (value.isDateTime()) {
                    obj.put(columnName,value.asDateTime());
                }
                if (value.isVertex()) {
                    obj.put(columnName,value.asNode());
                }
                if (value.isEdge()) {
                    obj.put(columnName,value.asRelationship());
                }
                if (value.isPath()) {
                    obj.put(columnName,value.asPath());
                }
                if (value.isList()) {
                    obj.put(columnName,value.asList());
                }
                if (value.isSet()) {
                    obj.put(columnName,value.asSet());
                }
                if (value.isMap()) {
                    obj.put(columnName,value.asMap());
                }
            }
            json.add(obj);
            System.out.println();
        }
        return json;
    }

    /**
     * return model Author
     * @param resultSet
     * @throws UnsupportedEncodingException
     */
    private static List<Author> returnAuthors(ResultSet resultSet) throws
            UnsupportedEncodingException {

        List<Author> authors = new ArrayList<Author>();
        for (int i = 0; i < resultSet.rowsSize(); i++) {
            ResultSet.Record record = resultSet.rowValues(i);
            JSONObject obj = new JSONObject();

            for (ValueWrapper value : record.values()) {
                if (value.isVertex()) {
                    Node node = value.asNode();
                    String nid = node.getId().asString();
                    obj.put("id", nid);
                    List<String> props = node.keys("author");
                    List<ValueWrapper> vals = node.values("author");
                    for (int j = 0; j < props.size(); j++){
                        obj.put(props.get(j), vals.get(j).asString());
                    }

                }
            }
            log.info("This is the result of returnAuthors" + obj.toString());
            Author author = JSON.toJavaObject(obj, Author.class);
            authors.add(author);
        }
        return authors;
    }

    /**
     * return model poetry
     * @param resultSet
     * @throws UnsupportedEncodingException
     */
    private static List<JSONObject> returnPoetryList(ResultSet resultSet) throws
            UnsupportedEncodingException {

        List<JSONObject> jsonList = new ArrayList<JSONObject>();

        for (int i = 0; i < resultSet.rowsSize(); i++) {
            ResultSet.Record record = resultSet.rowValues(i);
            JSONObject obj = new JSONObject();

            for (ValueWrapper value : record.values()) {
                if (value.isVertex()) {
                    Node node = value.asNode();
                    String nid = node.getId().asString();
                    obj.put("id", nid);
                    List<String> props = node.keys("poetry");
                    List<ValueWrapper> vals = node.values("poetry");
                    for (int j = 0; j < props.size(); j++){
                        if (vals.get(j).isDouble()) {
                            obj.put(props.get(j), vals.get(j).asDouble());
                        }
                        if (vals.get(j).isString()) {
                            obj.put(props.get(j),
                                    vals.get(j).asString());
                        }
                    }

                }
            }

            log.info("This is the result of returnJSONList" + obj.toString());

            jsonList.add(obj);
        }
        return jsonList;
    }
}