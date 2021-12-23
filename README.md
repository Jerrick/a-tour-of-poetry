# 项目名称
一首古诗带来的图数据库大冒险（a-tour-of-poetry）

## 项目简介
把中国古诗词放到图谱里，通过【作者搜索】关联出他所有的诗词，并关联出所有的创作地点，这就形成了一条【诗词巡礼】的旅游路线；通过【地点搜索】关联出当地所有的古诗词创作地，这就形成了一个充满文化气息的诗词城市。近些年，我们能看到中国古诗词在复兴，比如火出圈的《中国诗词大会》，以及一些古诗词的领域的大V、Up主们，在古诗词复兴的背景下，我们通过图数据库、知识图谱等技术做一些好玩的尝试，带给大家基于诗词的新体验，万一能促进一种新的旅游形式或者让大家都开始寻找自己的本命诗，那就功德无量了。

## 项目背景
1）一次带娃的时候，当我读到苏轼的《临江仙 风水洞作》
四大从来都遍满，此间风水何疑。故应为我发新诗。幽花香涧谷，寒藻舞沦漪。借与玉川生两腋，天仙未必相思。还凭流水送人归。层巅余落日，草露已沾衣。
这首词很美，但是我穷尽想象也无法体会到，我在想，如果我能回到这首词的创作地点，是不是就能欣赏到了？

示意图

![QQ20211209-204921](https://user-images.githubusercontent.com/1468472/145745839-97bb1305-6471-4c6b-8538-1dec40a41d52.png)


2）又一次带娃学古诗，他不是很喜欢，毕竟才五岁。我发现王之涣的《凉州词》
黄河远上白云间，一片孤城万仞山。羌笛何须怨杨柳，春风不度玉门关。
中竟然有他全部的名字，我说，这就是你的本命诗，然后他真的背了下来，也对其他诗词产生了兴趣。


## 项目价值


我们通过图数据库，构建古诗词图谱，通过技术在中国古典诗词上做一些好玩的尝试，是一款基于图数据库的toC产品。
希望通过图数据库的形式，带给大家新的诗词体验，万一能促进一种新的旅游形式或者让大家都开始寻找自己的本命诗，那就功德无量了。


## 项目设计

详细说明该项目系统架构，当中主要模块的设计介绍，参考以下内容：

* 项目系统结构
![image](https://user-images.githubusercontent.com/1468472/147273314-b7a10a2d-547a-4d5e-b3d6-c5e7fe12c288.png)


注：以上内容按需描述相应项，不涉及则无需体现。

## 项目测试

1. http://localhost:8787/api/test

2. http://localhost:8787/api/author?name=李白

[{"id":"author002","name":"李白","dynasty":"唐","courtesyName":"太白","pseudonym":"青莲居士"}]

3. http://81.70.29.163:8787/api/poetry?query=李白

[{"address":"绵阳市，匡山","author":"李白","ancient_address":"四川昌隆县北五十里，戴天山","name":"《访戴天山道士不遇》","Latitude":31.864654541015625,"id":"poetry009","content":"犬吠水声中，桃花带露浓。\n树深时见鹿，溪午不闻钟。\n野竹分青霭，飞泉挂碧峰。\n无人知所去，愁倚两三松。","longitude":104.68647766113281},{"address":"九江市，三叠泉大瀑布","author":"李白","ancient_address":"九江，庐山","name":"《望庐山瀑布》","Latitude":29.549285888671875,"id":"poetry010","content":"日照香炉生紫烟，遥看瀑布挂前川。\n飞流直下三千尺，疑是银河落九天。","longitude":116.06073760986328}]

4. http://localhost:8787/api/poetry/author?author=李白

[{"address":"绵阳市，匡山","author":"李白","ancient_address":"四川昌隆县北五十里，戴天山","name":"《访戴天山道士不遇》","Latitude":31.864654541015625,"id":"poetry009","content":"犬吠水声中，桃花带露浓。\n树深时见鹿，溪午不闻钟。\n野竹分青霭，飞泉挂碧峰。\n无人知所去，愁倚两三松。","longitude":104.68647766113281},{"address":"九江市，三叠泉大瀑布","author":"李白","ancient_address":"九江，庐山","name":"《望庐山瀑布》","Latitude":29.549285888671875,"id":"poetry010","content":"日照香炉生紫烟，遥看瀑布挂前川。\n飞流直下三千尺，疑是银河落九天。","longitude":116.06073760986328}]

5. http://localhost:8787/api/poetry/city?city=杭州

[{"address":"杭州，锦绣风水洞","author":"苏轼","ancient_address":"杭州，钱塘县","name":"《临江仙 风水洞作》","Latitude":30.125019073486328,"id":"poetry001","content":"四大从来都遍满，此间风水何疑。故应为我发新诗。幽花香涧谷，寒藻舞沦漪。\n借与玉川生两腋，天仙未必相思。还凭流水送人归。层巅余落日，草露已沾衣。","longitude":120.05693817138672},{"address":"杭州，西湖","author":"苏轼","ancient_address":"钱塘县，西湖","name":"《饮湖上初晴后雨二首·其二》","Latitude":30.228931427001953,"id":"poetry002","content":"水光潋滟晴方好，山色空蒙雨亦奇。\n欲把西湖比西子，淡妆浓抹总相宜。","longitude":120.12792205810547}]


6. http://localhost:8787/api/poetry/username?username=叶小萌
[{"address":"绍兴市，陆游故里","author":"陆游","ancient_address":"绍兴市","name":"《後园闲步》","Latitude":30.02001190185547,"id":"poetry011","content":"木叶勾萌喜小春，鬓毛萧飒媿陈人。\n今朝忽破簿书梦，此地暂还风月身。\n闲岸纶巾照清浅，却扶藤杖上嶙峋。\n人生要是便疏豁，金马银台莫问津。","longitude":120.53633880615234}]

