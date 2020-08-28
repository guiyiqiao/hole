# 树洞有声
## 背景
现代青年群体社会压力大，日常宣泄压力及寻求情感共鸣的轻成本渠道不多，线上减压渠道异军突起  
### 问题详述：
倾诉        ——宣泄压力：现实中虽然存在心理咨询室及线上咨询师，但由于经济、时间、精力成本较高，且存在对个人隐私安全性的不确定性，不适用于常态轻压力人群;
寻觅知己——情感共鸣：寻找知己/某方面知己较难
倾听        ——价值实现：希望/愿意安慰开导别人，但因个人生活节奏无法与某单一对象建立长期一对一联系群体，希望获得基于“弱联系”基础上的社交体验

### 要达成的目标是什么？（Goals） 

| 小程序端 | 功能点   | 功能描述           | 目标                               |
| -------- | -------- | :----------------- | ---------------------------------- |
|功能一|	发布心事|	用户可发布个人心事	|降低压力宣泄成本，快捷达成情感释放|
|	|选择公开心事|	用户在发布个人心事时，可选择是否公开（会展示在心事大厅，但只有用户本身可见，且有加密标志）	|提升用户隐私体验|
|	|昵称可修改|	心事卡片显示发布者昵称  （昵称长度为2~8个汉字，或3~16个字符）|	提升用户隐私体验，降低信息泄露风险|
|	|拥抱|	用户可以给心事一个“拥抱”	|提升用户归属感及认同感|
|功能二	|回应心事	|用户可回复其他用户心事	|快捷完成回复开导行为，提升用户价值获得感|
|	|选择公开回应|	收到回应的用户可选择是否公开该回应	|提升用户掌控感及隐私体验|
|	|喜欢|	用户可以给回应一个“喜欢”|	提升用户归属感及认同感|
|功能三|	消息记录|	用户可查看发布、回复、收到的内容消息|	提升用户价值感，满足人性收集心理|
|	|时光轴	| 1.消息记录展示机制（按时间逆序排序）: x年x月x日                               5：00-8:59       清晨                                                                                                         9：00-10:59     早上                                                                                           11:00-12:59      午间                                                                                         13:00-16:59      午后                                                                                               17:00-18:59      傍晚                                                                                      19:00-20:59      黄昏                                                                                         21:00-22:59      夜晚                                                                                                             23：00-4：59   夜深                                                                                                   2.搜索日历——按日期查找 |1.按时间段展示记录：                          a.优化用户心事积累体验及专属记录页面体验，提升用户归属感及依赖感         b.不具体到时间点，提升用户故事及隐私体验  2.搜索：  提升用户便捷搜索体验|
||自传播：手账页面|	可申请入住树洞获取专属地址（入住成功后该页面可生成分享卡片）|	提升用户归属感及体验趣味性|

## 数据库的设计

![数据库模型图](.\model.png)

## 注：字段的修改
blog :  
release_time -> publish_time  
state -> overt公开的 默认为false  


blog_report举报表
content -> reason 举报原因

comment回声表：
release_time -> publish_time  
state -> overt公开的 默认为0 false 私密
tip_off -> report举报


comment_report回声举报表
content -> reason