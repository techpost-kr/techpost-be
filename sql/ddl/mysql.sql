create table TechBlogPosts
(
    techBlogPostSeq   bigint auto_increment comment '기술 블로그 게시글 순번',
    techBlogEnum      varchar(30) comment '블로그 출처 구분',
    title             varchar(400) comment '게시글 제목',
    url               varchar(400) comment '원문 URL',
    publishedDateTime datetime comment '게시 일시',
    createdBy         bigint comment '생성자',
    createdDateTime   datetime default current_timestamp comment '생성 일시',
    modifiedBy        bigint comment '수정자',
    modifiedDateTime  datetime default current_timestamp on update current_timestamp comment '수정 일시',
    primary key (techBlogPostSeq)
) comment = '기술 블로그 게시글';


create table SlackWebhooks
(
    slackWebhookSeq  bigint auto_increment comment '슬랙 웹훅 순번',
    slackWebhookEnum varchar(30) comment '슬랙 웹훅 구분',
    url              varchar(255) comment '원문 URL',
    createdBy        bigint comment '생성자',
    createdDate      datetime default current_timestamp comment '생성 일시',
    modifiedBy       bigint comment '수정자',
    modifiedDate     datetime default current_timestamp on update current_timestamp comment '수정 일시',
    primary key (slackWebhookSeq)
) comment = '슬랙 웹훅 설정';

create table Users
(
    userSeq                    bigint auto_increment comment '사용자 순번',
    id                         varchar(255) comment '로그인 ID',
    password                   varchar(255) comment '비밀 번호',
    name                       varchar(255) comment '사용자 이름',
    userRoleEnum               varchar(30) comment '사용자 역할 구분',
    userStatusEnum             varchar(30) comment '사용자 상태 구분',
    lastLoginIp                varchar(255) comment '마지막 로그인 IP',
    lastLoginDateTime          datetime comment '마지막 로그인 일시',
    lastPasswordChangeDateTime datetime comment '마지막 비밀번호 변경 일시',
    failedLoginAttempts        int comment '로그인 실패 횟수',
    primary key (userSeq)
) comment = '사용자';

