# !/usr/bin/env python3
# -*- coding: utf-8 -*-


import os
from tiktok.utils import Constant


constant = Constant()
constant.BASE_DIR = os.path.dirname(os.path.abspath(__file__))
constant.TIKTOK_DB_PATH = os.path.join(
    os.path.join(constant.BASE_DIR, "database"), "tiktok.sqlite"
)


constant.DB_SUCCESS = 1
constant.DB_FAILURE = 2