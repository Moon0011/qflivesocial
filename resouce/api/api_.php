<?php

video.520cai.cn
//验证码获取
/index.php/api/login/verify
传值
    phone------------登录名
返回值
    {"msg":"y"}
    a ---------手机号码不正确或为空
    b ---------验证码过期
    y ---------验证码发送成功


//登录
/index.php/api/login/dologin
  传值
    phone------------登录名
	code ------------验证码
  返回值
{"msg":"y","result":{"uid":11,"username":"18601230114","sign":"eJxNjV1vgjAYRv9Lb11mCy3YJbvwi8WgUnSyLDFpOihaDZVBB5pl-32ImO32nPe8zzd4na8fRRyfvrTh5pJL8AQgeGixSqQ2KlWyaCAaOBBZNkQId1rkuUq4MNwukn9VmRx5q64RhhDaLsFuJ*U5V4XkIjW3p4QQqznpbCWLUp10IyyIyHUN-kmjMtkm0HIpoY5931O7Bi*mm-EsnGymg0mPZb7e9oOxobon8TFilifY4SUOs2jbf-PmoygYZTWtZ7vgsj*sl04deGkYfYq4StmyKh1vj32xosOPYSB89r5Cmbt4Bj*-D9lX5g__"}}
    uid--------用户ID
    username------登录名
    sign---------校验码

    a--------验证码不匹配

//登录后的完善页面
/index.php/api/user/perfectinfo
传值
    uid------------------用户ID
    anchorpic------------用户图像
    nickname-------------用户昵称
    sex------------------用户性别
返回值
{"msg":"y"}
a--------------值不能为空
y--------------更新完成


//今日推荐
/index.php/api/user/dayrecommend
传值  uid
无
返回值
{"msg":"y","result":[{"uid":5,"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/a7c7fa9fa53429bac09861f09c7064a5.jpg","nickname":"\u6d4b\u8bd53","age":null,"totaltime":""}]}
uid------------------用户ID
anchorpic------------用户图像
nickname-------------用户昵称
age------------------年龄

totaltime------------通话总时长（完善资料页不显示）


//首页主播列表分类
//1全部 按时长  2人气 按收益  3新秀 按注册时间  认证 暂时不要
/index.php/api/user/recommend
传值 uid
type------------------类别
返回值
{"msg":"y","result":[{"uid":4,"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/4454bcd62222d9bcf830cc2efe8c3323.jpg","nickname":"\u6d4b\u8bd52","age":null,"signature":"\u6d4b\u8bd5","labels":"","totaltime":"2468"}]}
uid------------------用户ID
anchorpic------------用户图像
nickname-------------用户昵称
age------------------年龄
labels---------------个人标签
signature------------个人签名


//轮播图
/index.php/api/user/slide
传值
无
返回值
{"msg":"y","result":[{"id":2,"title":"\u8fd9\u662f\u4e00\u4e2a\u6d4b\u8bd5","url":"","pic":"\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","status":1,"order":0},{"id":4,"title":"1111","url":"111111111","pic":"video.520cai.cn\/public\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","status":1,"order":1},{"id":5,"title":"11111111111","url":"111111","pic":"video.520cai.cn\/public\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","status":1,"order":0},{"id":6,"title":"11","url":"11","pic":"video.520cai.cn\/public\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","status":1,"order":0}]}
id---------------礼物ID
title---------------礼物名称
url---------------链接地址（暂时为空）
pic---------------礼物图片
status-------------是否显示
order-------------礼物排序（排序越小越靠前）


//排行榜
/index.php/api/user/ranklist
传值
type-------------排行榜类别 1日榜 2周榜 3总榜
stype-------------子类别  1关注榜 2富豪榜 3魅力榜
返回值
{"msg":"y","result":[{"uid":3,"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"\u6d4b\u8bd51","attentionnum":2},{"uid":4,"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/4454bcd62222d9bcf830cc2efe8c3323.jpg","nickname":"\u6d4b\u8bd52","attentionnum":2},{"uid":5,"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/a7c7fa9fa53429bac09861f09c7064a5.jpg","nickname":"\u6d4b\u8bd53","attentionnum":1}]}
uid------------------用户ID
anchorpic------------用户图像
nickname-------------用户昵称

   attentionnum------------------关注榜 关注总数
   outnum------------------富豪榜 送出总数
   oinnum------------------魅力榜 收到总数


//个人详情页
/index.php/api/user/website
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"video":null,"voice":null,"nickname":"\u6d4b\u8bd52","sex":"\u7537","onlinestatus":false,"constellation":null,"address":null,"photo":null,"listen_setting":null,"video_setting":null,"voice_setting":null,"signature":"\u6d4b\u8bd5","labels":"","commentnum":2,"rating":4,"attentionnum":2,"totaltime":172860}}
video-----------------认证视频地址
voice-----------------认证声音地址
nickname-----------------用户昵称
sex------------------用户性别
onlinestatus-----------------在线状态
constellation-----------------星座
address-----------------地址
photo-----------------相册
listen_setting-----------------接听设置
video_setting-----------------视频收费设置
voice_setting-----------------语音收费设置
signature-----------------个人签名
labels-----------------个人标签
commentnum-----------------评论数
rating-----------------好评度
attentionnum-----------------关注数
totaltime-----------------直播总时长


//个人礼物清单
/index.php/api/user/giftlist
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"nums":1,"gift":[{"giftname":"11","gifticon":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","counts":1}]}}
nums---------------礼物总个数
gift
    giftname------------礼物名称
    gifticon------------礼物图片
    counts-------------礼物个数


//修改个人资料
/index.php/api/user/editinfo
传值
uid------------------用户ID
type-----------------1用戶图像 2昵称 3个人签名 4标签 5地址 6出生日期  7性别 8设置背景图片 9设置相册图片
anchorpic------------1用戶图像
nickname------------2昵称
signature------------3个人签名
label------------4标签 (多个以 ，隔开  例如1,2,3)
address------------5地址
birthday------------6出生日期
sex------------7性别
headpic------------8设置背景图片
photo------------9设置相册图片
返回值
{"msg":"y"}
y-----------修改成功
a-----------不能为空

//删除图片
/index.php/api/user/delphoto
传值(uid?)
id--------------------图片id


//个人中心
/index.php/api/user/usercenter
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/4454bcd62222d9bcf830cc2efe8c3323.jpg","roompic":"http:\/\/video.520cai.cn\/static\/images\/default-thumbnail.png","nickname":"\u6d4b\u8bd52","curroomnum":1833648681,"signature":"\u6d4b\u8bd5","age":null,"sex":"\u7537","photo":[]}}
anchorpic------------用户图像
roompic--------------背景图片
nickname-------------用户昵称
curroomnum-----------房间号
signature------------个人签名
age------------------年龄
sex------------------用户性别
photo------------------用户相册


//个人资料
/index.php/api/user/userinfo
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/4454bcd62222d9bcf830cc2efe8c3323.jpg","nickname":"\u6d4b\u8bd52","signature":"\u6d4b\u8bd5","labelinfo":"","address":null,"birthday":null,"sex":"\u7537","constellation":null}}
anchorpic------------用户图像
nickname-------------用户昵称
signature------------个人签名
labelinfo-------------个人标签
address-----------------地址
birthday---------------出生日期
sex------------------用户性别
constellation-----------------星座


//获取标签种类
/index.php/api/user/label
传值
type------------标签类型 1为个人设置 2为用户设置
返回值
{"msg":"y","result":[{"id":3,"content":"\u7f8e\u5973","type":2},{"id":4,"content":"\u53ef\u4eba\u513f","type":2},{"id":14,"content":"\u8f6f\u59b9\u5b50","type":2},{"id":15,"content":"\u7ae5\u989c","type":2}]}
id---------------礼物ID
content----------礼物名称


//我的账户
/index.php/api/user/account
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"daycoins":0,"monthcoins":0,"balance":10}}
daycoins------------今日收益
monthcoins------------当月收益
balance------------账户余额


//更新账户金额
/index.php/api/user/updatebalance
传值
uid------------------用户ID
money---------------消费金额
返回值
{"msg":"y"}
y--------------更新成功
a--------------更新失败


//送出礼物页
/index.php/api/user/giftlists
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"balance":10,"giftinfo":[{"giftname":"1111","gifticon":"\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","needcoin":111},{"giftname":"11","gifticon":"\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","needcoin":111}]}}
balance--------------账户余额

giftname-------------礼物名称
gifticon-------------礼物图像
needcoin-------------礼物钱数


//礼物页
/index.php/api/user/gift
传值
uid------------------用户ID
type------------礼物类型  1收到的礼物 2送出的礼物
返回值
{"msg":"y","result":[{"giftname":"1111","gifticon":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","giftnum":1,"nickname":"11"},{"giftname":"1111","gifticon":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","giftnum":1,"nickname":"11"},{"giftname":"1111","gifticon":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","giftnum":2,"nickname":"11"}]}
giftname-------------礼物名称
gifticon-------------礼物图像
giftnum--------------礼物个数
nickname-------------送出礼物的用户昵称


//送礼物
/index.php/api/user/addgift
传值
uid---------------送出礼物的用户ID
touid-------------接收礼物的用户ID
giftid------------礼物ID
giftnum-----------礼物数量
返回值
{"msg":"y"}
y--------------插入成功
a---------------插入失败
c--------------余额不足


//通话设置
/index.php/api/user/callsetting
传值
uid------------------用户ID
voice----------------声音设置
video----------------视频设置
listen---------------接听设置
返回值
{"msg":"y"}
y--------------更新成功
a---------------更新失败


//关注页
/index.php/api/user/attpage
传值
uid------------------用户ID
type-----------------类别 //1关注 2粉丝
返回值
{"msg":"y","result":[{"nickname":"\u6d4b\u8bd5","anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170808\/895542cc7037f724b31ea5ad69ad58a1.jpg","sex":"\u5973","age":null,"signture":"111"},{"nickname":"11","anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170808\/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","sex":"\u5973","age":null,"signture":"1111"}]}

nickname-------------用户昵称
anchorpic------------用户图像
sex------------------用户性别
age------------------年龄
signature------------个人签名


//关注
/index.php/api/user/attention
传值
uid------------------关注用户ID
attuid---------------被关注用户ID
attstatus------------关注状态 0 1
返回值
{"msg":"y"}
y--------------插入成功
a---------------插入失败


//评价
/index.php/api/user/comment
传值
uid------------------评价用户ID
touid----------------被评价用户ID
starnum--------------星数
content--------------评价内容
label----------------印象标签   多个,号隔开
返回值
{"msg":"y"}
y--------------插入成功
a---------------插入失败


//单个评价详情页
/index.php/api/user/commentdetail
传值
id---------------评论ID
返回值
{"msg":"y","result":{"content":"111111","starnum":4,"creattime":1503299113,"label":["\u65c5\u6e38\u8fbe\u4eba","\u7f8e\u5973"],"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/4454bcd62222d9bcf830cc2efe8c3323.jpg","nickname":"\u6d4b\u8bd52"}}

content--------------评价内容
starnum--------------星数
creattime------------创建时间
label----------------印象标签
anchorpic------------评论的用户图像
nickname-------------评论的用户昵称


//通话评价详情页
/index.php/api/user/callevaluation
传值
uid------------------用户ID
返回值
{"msg":"y","result":{"commentinfo":[{"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"\u6d4b\u8bd51","content":"111111","starnum":4,"creattime":1503299050,"label":["\u65c5\u6e38\u8fbe\u4eba","\u7f8e\u5973"]},{"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"\u6d4b\u8bd51","content":"111111","starnum":4,"creattime":1503299100,"label":["\u65c5\u6e38\u8fbe\u4eba","\u7f8e\u5973"]}],"labelinfo":["\u65c5\u6e38\u8fbe\u4eba","\u7f8e\u5973",{"content":"\u65c5\u6e38\u8fbe\u4eba","counts":2},{"content":"\u7f8e\u5973","counts":2}],"userinfo":{"anchorpic":"http:\/\/video.520cai.cn\/upload\/zhibo\/20170817\/4454bcd62222d9bcf830cc2efe8c3323.jpg","roompic":null,"nickname":"\u6d4b\u8bd52","curroomnum":1833648681,"sex":"\u7537","age":null,"signature":"\u6d4b\u8bd5"}}}
commentinfo---------评论信息
    content--------------评价内容
    starnum--------------星数
    creattime------------创建时间
    label----------------印象标签
    anchorpic------------评论的用户图像
    nickname-------------评论的用户昵称
userinfo-------------个人信息
     anchorpic------------用户图像
     roompic--------------用户背景图片
     nickname-------------用户昵称
     curroomnum-----------用户房间号
     sex------------------性别
     age------------------年龄
     signature------------个人签名
labelinfo-----------印象标签信息
     content--------标签内容
     counts---------该标签累计个数

















