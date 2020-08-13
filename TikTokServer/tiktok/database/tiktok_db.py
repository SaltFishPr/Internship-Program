# !/usr/bin/env python3
# -*- coding: utf-8 -*-
import sqlite3
import random
import string
from tiktok.my_values import constant
from .contract import UserEntry,RelationEntry


class UserTable:
    """
    TikTok用户表的数据库方法
    """
    @classmethod
    def create_table(cls):
        """
        创建用户表
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        cursor.execute(
            f"CREATE TABLE {UserEntry.TABLE_NAME} ("
            f"id INTEGER PRIMARY KEY AUTOINCREMENT, "
            f"{UserEntry.COLUMN_PHONE} TEXT NOT NULL, "
            f"{UserEntry.COLUMN_PASSWORD} TEXT NOT NULL, "
            f"{UserEntry.COLUMN_ACCOUNT} TEXT NOT NULL, "
            f"{UserEntry.COLUMN_NAME} TEXT NOT NULL, "
            f"{UserEntry.COLUMN_INTRODUCTION} TEXT, "
            f"{UserEntry.COLUMN_SEX} TEXT, "
            f"{UserEntry.COLUMN_BIRTHDAY} TEXT, "
            f"{UserEntry.COLUMN_REGION} TEXT); "
        )
        cursor.close()
        conn.close()

    @classmethod
    def query(cls,phone):
        """
        根据手机号查询用户
        :param phone:
        :return:元组数组 [()]，包含用户的所有信息
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"SELECT * FROM {UserEntry.TABLE_NAME} "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}'; "
        )
        cursor.execute(sql)
        res = cursor.fetchall()
        cursor.close()
        conn.close()
        if not res:
            return None
        return res[0]

    @classmethod
    def insert(cls,phone,password):
        """
        通过手机号和密码进行插入
        :param phone: 用户手机号
        :param password: 密码
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        account = 'dy'+''.join(random.sample(string.ascii_letters + string.digits, 8))  # 随机生成8位字符串
        name = '用户'+phone
        sql=(
            f"INSERT INTO {UserEntry.TABLE_NAME} "
            f"({UserEntry.COLUMN_PHONE}, {UserEntry.COLUMN_PASSWORD}, "
            f"{UserEntry.COLUMN_ACCOUNT}, {UserEntry.COLUMN_NAME}) "
            f"VALUES ('{phone}', '{password}', '{account}', '{name}');"
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_name(cls,phone,name):
        """
        用户更新自己的名字
        :param phone: 手机号
        :param name: 更新后的名字
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_NAME}='{name}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_introduction(cls,phone,introduction):
        """
        用户更新自己的简介
        :param phone: 手机号
        :param introduction:更新后的简介
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_INTRODUCTION}='{introduction}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_sex(cls,phone,sex):
        """
        用户更新自己的性别
        :param phone: 手机号
        :param sex: 更新后的性别
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_SEX}='{sex}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_birthday(cls,phone,birthday):
        """
        用户更新自己的生日
        :param phone: 手机号
        :param birthday: 更新后的生日
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_BIRTHDAY}='{birthday}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_region(cls,phone,region):
        """
        用户更新自己所在的地区
        :param phone: 手机号
        :param region: 更新后的地区
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_REGION}='{region}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_password(cls,phone,password):
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_PASSWORD}='{password}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def update_account(cls,phone,account):
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"UPDATE {UserEntry.TABLE_NAME} "
            f"SET {UserEntry.COLUMN_ACCOUNT}='{account}' "
            f"WHERE {UserEntry.COLUMN_PHONE}='{phone}' "
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def drop_table(cls):
        """
        删除当前用户表
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        cursor.execute(
            f"Drop TABLE {UserEntry.TABLE_NAME} ;"
        )
        cursor.close()
        conn.close()


    @classmethod
    def check_account(cls,account):
        """
        判断当前抖音帐号是否存在
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"SELECT * FROM {UserEntry.TABLE_NAME} "
            f"WHERE {UserEntry.COLUMN_ACCOUNT}='{account}'; "
        )
        cursor.execute(sql)
        res = cursor.fetchall()
        cursor.close()
        conn.close()
        if not res:
            return False
        return True


class RelationTable:
    """
    TikTok用户关系的数据库方法
    """
    @classmethod
    def create_table(cls):
        """
        创建用户关系表
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        cursor.execute(
            f"CREATE TABLE {RelationEntry.TABLE_NAME} ("
            f"id INTEGER PRIMARY KEY AUTOINCREMENT, "
            f"{RelationEntry.COLUMN_TIKTOKER} INTEGER NOT NULL, "
            f"{RelationEntry.COLUMN_FOLLOWER} INTEGER NOT NULL); "
        )
        cursor.close()
        conn.close()

    @classmethod
    def follow(cls,phone_tiktoker,phone_follower):
        """
        用户A 关注用户B A点击关注B后调用的方法
        :param phone_tiktoker:
        :param phone_follower:
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql=(
            f"INSERT INTO {RelationEntry.TABLE_NAME} "
            f"({RelationEntry.COLUMN_TIKTOKER}, {RelationEntry.COLUMN_FOLLOWER}) "
            f"VALUES ('{phone_tiktoker}', '{phone_follower}');"
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def query(cls,phone_tiktoker,phone_follower):
        """
        是否存在A关注B
        :param phone_tiktoker:
        :param phone_fpllower:
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"SELECT * FROM {RelationEntry.TABLE_NAME} "
            f"WHERE {RelationEntry.COLUMN_TIKTOKER}='{phone_tiktoker}' AND {RelationEntry.COLUMN_FOLLOWER}= '{phone_follower}'; "
        )
        cursor.execute(sql)
        res = cursor.fetchall()
        cursor.close()
        conn.close()
        if not res:
            return False
        return True

    @classmethod
    def remove_follow(cls,phone_tiktoker,phone_follower):
        """
        用户A 取消关注用户B A点击取消关注后调用的方法
        :param phone_tiktoker:
        :param phone_follower:
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"DELETE FROM {RelationEntry.TABLE_NAME} "
            f"WHERE {RelationEntry.COLUMN_TIKTOKER}='{phone_tiktoker}' AND {RelationEntry.COLUMN_FOLLOWER}='{phone_follower}'"
        )
        cursor.execute(sql)
        cursor.close()
        conn.commit()
        conn.close()

    @classmethod
    def get_target(cls,phone):
        """
        得到该用户的关注列表
        :param phone:用户
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"SELECT * FROM {RelationEntry.TABLE_NAME} "
            f"WHERE {RelationEntry.COLUMN_FOLLOWER}='{phone}'; "
        )
        cursor.execute(sql)
        res = cursor.fetchall()
        cursor.close()
        conn.close()
        if not res:
            return None
        res_list = []
        for item in res:
            res_list.append(item[1])
        return res_list

    @classmethod
    def get_follower(cls,phone):
        """
        得到该用户的粉丝列表
        :param phone:
        :return:
        """
        conn = sqlite3.connect(constant.TIKTOK_DB_PATH)
        cursor = conn.cursor()
        sql = (
            f"SELECT * FROM {RelationEntry.TABLE_NAME} "
            f"WHERE {RelationEntry.COLUMN_TIKTOKER}='{phone}'; "
        )
        cursor.execute(sql)
        res = cursor.fetchall()
        cursor.close()
        conn.close()
        if not res:
            return None
        res_list = []
        for item in res:
            res_list.append(item[2])
        return res_list