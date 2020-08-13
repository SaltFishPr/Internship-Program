from tiktok.database.tiktok_db import UserTable, RelationTable
from tiktok.my_values import constant
import requests, json


def user_table_test():
    # UserTable.create_table()
    # UserTable.insert('1234','123456')
    res = (UserTable.query('1234'))
    if res[2] == '123456':
        print(constant.SUCCESS)
    # UserTable.update_birthday(phone,'1999-10-23')
    # UserTable.update_introduction(phone,'日子和我都很难过:(')
    # UserTable.update_name(phone,'QiQi')
    # UserTable.update_region(phone,'二次元')
    # UserTable.update_sex(phone,'女')
    pass


def relation_table_test():
    phone1 = '12345678'
    phone2 = '13668683911'
    # RelationTable.create_table()
    # RelationTable.follow(phone1,phone2)
    # RelationTable.remove_follow(phone1,phone2)
    # print(RelationTable.query(phone1,phone2))
    # print(RelationTable.get_target(phone2))
    # print(RelationTable.get_follower(phone1))
    pass


def get_method_test():
    url = "http://192.168.0.106:5000/get_follower/1"
    res = requests.get(url=url)
    print(res.json())


def post_method_test():
    url = "http://192.168.0.106:5000/update_account"
    data = {"phone":'1','account':'test'}
    res = requests.post(url=url, data=data)
    print(res.json())


if __name__ == '__main__':
    # user_table_test()
    # relation_table_test()
    # RelationTable.follow('1234','13668683911')
    # UserTable.update_password('11111','1234')
    post_method_test()
    pass
