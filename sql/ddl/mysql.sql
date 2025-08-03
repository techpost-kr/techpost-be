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

create table BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID bigint       not null primary key,
    VERSION         bigint       null,
    JOB_NAME        varchar(100) not null,
    JOB_KEY         varchar(32)  not null,
    constraint JOB_INST_UN
        unique (JOB_NAME, JOB_KEY)
);

create table BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID bigint        not null auto_increment primary key,
    VERSION          bigint        null,
    JOB_INSTANCE_ID  bigint        not null,
    CREATE_TIME      datetime(6)   not null,
    START_TIME       datetime(6)   null,
    END_TIME         datetime(6)   null,
    STATUS           varchar(10)   null,
    EXIT_CODE        varchar(2500) null,
    EXIT_MESSAGE     varchar(2500) null,
    LAST_UPDATED     datetime(6)   null,
    constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
);

create table BATCH_JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   bigint        not null primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint JOB_EXEC_CTX_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID bigint        not null,
    PARAMETER_NAME   varchar(100)  not null,
    PARAMETER_TYPE   varchar(100)  not null,
    PARAMETER_VALUE  varchar(2500) null,
    IDENTIFYING      char          not null,
    constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  bigint        not null auto_increment primary key,
    VERSION            bigint        not null,
    STEP_NAME          varchar(100)  not null,
    JOB_EXECUTION_ID   bigint        not null,
    CREATE_TIME        datetime(6)   not null,
    START_TIME         datetime(6)   null,
    END_TIME           datetime(6)   null,
    STATUS             varchar(10)   null,
    COMMIT_COUNT       bigint        null,
    READ_COUNT         bigint        null,
    FILTER_COUNT       bigint        null,
    WRITE_COUNT        bigint        null,
    READ_SKIP_COUNT    bigint        null,
    WRITE_SKIP_COUNT   bigint        null,
    PROCESS_SKIP_COUNT bigint        null,
    ROLLBACK_COUNT     bigint        null,
    EXIT_CODE          varchar(2500) null,
    EXIT_MESSAGE       varchar(2500) null,
    LAST_UPDATED       datetime(6)   null,
    constraint JOB_EXEC_STEP_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  bigint        not null primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint STEP_EXEC_CTX_FK
        foreign key (STEP_EXECUTION_ID) references BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
);
