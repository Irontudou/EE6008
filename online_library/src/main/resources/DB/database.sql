#book表
create table book
(
    id              int auto_increment
        primary key,
    title           varchar(255)                               not null,
    author          varchar(255)                               not null,
    publisher       varchar(255)                               null,
    isbn            varchar(20)                                null,
    publishDate     date                                       null,
    category        text                                       null,
    cover           varchar(500)                               null,
    rating          double       default 0                     null,
    description     text                                       null,
    location        varchar(255) default 'Lee Wee Nam Library' null,
    callNumber      varchar(50)                                null,
    availableCopies int          default 20                    null,
    totalCopies     int          default 20                    null,
    available       int          default 1                     null,
    create_time     datetime     default CURRENT_TIMESTAMP     null,
    update_time     datetime     default CURRENT_TIMESTAMP     null,
    constraint isbn
        unique (isbn),
    constraint title
        unique (title)
)
    charset = utf8mb4;


#user表
create table user
(
    id       int auto_increment
        primary key,
    username varchar(255)  not null,
    name     varchar(255)  null,
    password varchar(255)  not null,
    role     int default 0 not null,
    constraint username
        unique (username)
)
    charset = utf8mb4;


#user表数据
INSERT INTO user (username, name, password, role)
VALUES
('春风拂柳细雨', '张伟', '12345678', '超级管理员'),
('秋水共长天', '李娜', '23456789', '管理员'),
('夏日微风轻', '王芳', '34567890', '管理员'),
('冬雪皑皑映', '赵强', '45678901', '普通用户'),
('明月松间照', '刘洋', '56789012', '普通用户'),
('清风徐来水', '陈静', '67890123', '普通用户'),
('夜空繁星点', '杨涛', '78901234', '普通用户'),
('碧波荡漾情', '黄丽', '89012345', '普通用户'),
('桃李春风暖', '周峰', '90123456', '普通用户'),
('流云轻拂面', '吴敏', '01234567', '普通用户'),
('落霞与孤鹜', '郑凯', '12345098', '普通用户'),
('白鹭洲畔影', '冯霞', '23456109', '普通用户'),
('青松挺且直', '宋宇', '34567210', '普通用户'),
('晨曦初照山', '何欣', '45678321', '普通用户'),
('花香满园春', '许磊', '56789432', '普通用户'),
('红叶随风舞', '邓杰', '67890543', '普通用户'),
('微雨燕双飞', '沈露', '78901654', '普通用户'),
('渔舟唱晚风', '吕浩', '89012765', '普通用户'),
('江南烟雨蒙', '丁雯', '90123876', '普通用户'),
('日落西山红', '曹亮', '01234987', '普通用户');


#评论表
create table comments
(
    id          int auto_increment
        primary key,
    book_id     int                                 null,
    content     text                                not null,
    rating      decimal(10, 2)                      null,
    create_time timestamp default CURRENT_TIMESTAMP null,
    create_user int                                 null,
    update_time timestamp default CURRENT_TIMESTAMP null,
    update_user int                                 null,
    check (`rating` between 0 and 10),
    constraint rating
        check (`rating` in (1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5)),
    constraint rating_check
        check (`rating` in (1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5))
);


#联系我们
create table contact_form
(
    id          int auto_increment
        primary key,
    email       varchar(255) not null,
    subject     varchar(255) not null,
    message     varchar(255) not null,
    create_user int          not null,
    create_time datetime     null
)
    charset = utf8mb4;


#购书请求
create table request_form
(
    id          int auto_increment
        primary key,
    title       varchar(255)  not null,
    author      varchar(255)  not null,
    isbn        varchar(255)  null,
    publisher   varchar(255)  null,
    publishYear date          null,
    reason      varchar(255)  not null,
    status      int default 0 null,
    create_time datetime      null,
    create_user int           null,
    update_time datetime      null,
    update_user int           null,
    constraint isbn
        unique (isbn)
)
    charset = utf8mb4;