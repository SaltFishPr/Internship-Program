from flask import Flask,request
from tiktok.database.tiktok_db import UserTable,RelationTable
from tiktok.my_values import constant
import json


app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/register',methods=['POST'])
def register():
    """
    用户注册
    :return:
    """
    phone = request.form["phone"]
    password = request.form["password"]
    res = UserTable.query(phone)
    if res is None:
        UserTable.insert(phone,password)
        return json.dumps({"data":'注册成功',"ret_code":constant.DB_SUCCESS})
    else:
        return json.dumps({"data": '帐号已存在', "ret_code": constant.DB_FAILURE})


@app.route('/login',methods=['POST'])
def login():
    """
    用户登录
    :return:
    """
    phone = request.form["phone"]
    password = request.form["password"]
    print(phone)
    print(password)
    res = UserTable.query(phone)
    print(res)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    if res[2] == password:
        return json.dumps({"data":'登录成功',"ret_code":constant.DB_SUCCESS})
    else:
        return json.dumps({"data": '密码错误', "ret_code": constant.DB_FAILURE})


@app.route('/update_name',methods=['POST'])
def update_name():
    """
    根据手机号码更换用户名
    :return:
    """
    phone = request.form["phone"]
    name = request.form["name"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    UserTable.update_name(phone,name)
    return json.dumps({"data": '修改成功', "ret_code": constant.DB_SUCCESS})


@app.route('/update_password',methods=['POST'])
def update_password():
    """
    根据手机号更换密码
    :return:
    """
    phone = request.form["phone"]
    old_password = request.form["old_password"]
    new_password = request.form["new_password"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    if res[2] == old_password:
        UserTable.update_password(phone,new_password)
        return json.dumps({"data":'密码修改成功',"ret_code":constant.DB_SUCCESS})
    else:
        return json.dumps({"data": '旧密码输入错误', "ret_code": constant.DB_FAILURE})


@app.route('/update_account',methods=['POST'])
def update_account():
    """
    根据手机号码更换抖音账户名
    :return:
    """
    phone = request.form["phone"]
    account = request.form["account"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    UserTable.update_account(phone, account)
    return json.dumps({"data": '修改成功', "ret_code": constant.DB_SUCCESS})


@app.route('/update_introduction',methods=['POST'])
def update_introduction():
    """
    根据手机号码更换简介
    :return:
    """
    phone = request.form["phone"]
    introduction = request.form["introduction"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    UserTable.update_introduction(phone, introduction)
    return json.dumps({"data": '修改成功', "ret_code": constant.DB_SUCCESS})


@app.route('/update_sex',methods=['POST'])
def update_sex():
    """
    根据手机号码更换性别
    :return:
    """
    phone = request.form["phone"]
    sex = request.form["sex"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    UserTable.update_sex(phone, sex)
    return json.dumps({"data": '修改成功', "ret_code": constant.DB_SUCCESS})


@app.route('/update_birthday',methods=['POST'])
def update_birthday():
    """
    根据手机号码更换生日
    :return:
    """
    phone = request.form["phone"]
    birthday = request.form["birthday"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    UserTable.update_birthday(phone, birthday)
    return json.dumps({"data": '修改成功', "ret_code": constant.DB_SUCCESS})


@app.route('/update_region',methods=['POST'])
def update_region():
    """
    根据手机号码更换地区
    :return:
    """
    phone = request.form["phone"]
    region = request.form["region"]
    res = UserTable.query(phone)
    if res is None:
        return json.dumps({"data": '帐号不存在', "ret_code": constant.DB_FAILURE})
    UserTable.update_region(phone, region)
    return json.dumps({"data": '修改成功', "ret_code": constant.DB_SUCCESS})


@app.route("/get_info/<phone>")
def get_info(phone):
    """
    得到个人信息
    :return:
    """
    if not UserTable.query(phone):
        return json.dumps({"data": None, "ret_code": constant.DB_FAILURE})
    res = UserTable.query(phone)
    data = {
        'phone':res[1],
        'account':res[3],
        'nickname':res[4],
        'introduction':res[5],
        'sex':res[6],
        'birthday':res[7],
        'region':res[8]
    }
    print(data)
    return json.dumps({"data": data, "ret_code": constant.DB_SUCCESS})


@app.route("/follow",methods=['POST'])
def follow():
    """
    用户点击关注后，添加关系数据库
    :return:
    """
    user_phone = request.form["user"]
    target_phone = request.form["target"]
    res_user = UserTable.query(user_phone)
    res_target = UserTable.query(target_phone)
    if res_user is None or res_target is None:
        return json.dumps({"data": "关注失败", "ret_code": constant.DB_SUCCESS})
    RelationTable.follow(target_phone,user_phone)
    return json.dumps({"data": "关注成功", "ret_code": constant.DB_SUCCESS})


@app.route("/get_follower/<phone>")
def get_follwer(phone):
    """
    得到用户的粉丝列表
    :param phone:
    :return: 粉丝列表
    """
    res = RelationTable.get_follower(phone)
    return json.dumps({"data": res, "ret_code": constant.DB_SUCCESS})


@app.route("/get_target/<phone>")
def get_target(phone):
    """
    得到用户的关注列表
    :param phone:
    :return: 关注的用户列表
    """
    res = RelationTable.get_target(phone)
    return json.dumps({"data": res, "ret_code": constant.DB_SUCCESS})


@app.route("/remove_follow",methods=['POST'])
def remove_follow():
    """
    用户取消关注
    :return:
    """
    user_phone = request.form["user"]
    target_phone = request.form["target"]
    if not RelationTable.query(target_phone,user_phone):
        return json.dumps({"data": "取消关注失败", "ret_code": constant.DB_SUCCESS})
    RelationTable.remove_follow(target_phone, user_phone)
    return json.dumps({"data": "取消关注成功", "ret_code": constant.DB_SUCCESS})


if __name__ == '__main__':
    app.run(host="0.0.0.0")
