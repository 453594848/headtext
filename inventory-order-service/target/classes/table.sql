create table product_stock_47009
(
    sku_code           bigint auto_increment comment '产品编码',
    sku_name           varchar(50) not null comment '产品名称',
    saleable_inventory int         not null comment '可售库存',
    constraint product_stock_47009_pk
        primary key (sku_code)
);
CREATE TABLE log_message_47009 (
    request_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '请求ID',
    created DATETIME NOT NULL COMMENT '创建时间',
    message VARCHAR(50) NOT NULL COMMENT '日志信息'
);
