# !/usr/bin/env python3
# -*- coding: utf-8 -*-


class UserEntry:
    TABLE_NAME = "users"  # 数据库-表名
    COLUMN_NAME = "nickname"  # 用户-昵称
    COLUMN_PHONE = "phone"  # 用户-手机号（账户） 后期不可更改
    COLUMN_PASSWORD = "password"  # 用户-密码
    COLUMN_ACCOUNT = "account"  # 用户-抖音号
    COLUMN_INTRODUCTION = "introduction"  # 用户-简介
    COLUMN_SEX = "sex"  # 用户-性别
    COLUMN_BIRTHDAY = "birthday"  # 用户-生日
    COLUMN_REGION = "region"  # 用户-地区


class RelationEntry:
    """
    每一对键值对代表着 follower为 tiktoker的粉丝
    """
    TABLE_NAME = "relations"  # 数据库-表名
    COLUMN_TIKTOKER = "tiktoker"  # 关系-视频作者手机号
    COLUMN_FOLLOWER = "follower"  # 关系-作者的粉丝手机号
