-- create database wechatofficial;
use wechatofficial;
create table access_token
(
    id int primary key auto_increment,
    app_id varchar(100), /*公众号标识，未来可能可以拓展多个公众号，同时为多个公众号服务*/
    access_token varchar(512), /*调用wechat api的token*/
    gmt_token_expire bigint /*token过期时间*/
);