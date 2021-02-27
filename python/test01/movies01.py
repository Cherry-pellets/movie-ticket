# encoding: utf-8
"""
@author: lanxiaofang
@contact: fang@lanxf.cn
@software: PyCharm
@file: doubanapi.py
@time: 2020/4/11 11:34
"""
import datetime
import requests
from time import sleep
import pymysql
import random

header = {
    'Accept': 'application / json, text / plain, * / *',
    'Accept - Encoding': 'gzip, deflate, br',
    "Accept - Language": "zh - CN, zh;q=0.9",
    'Connection': "keep - alive",
    'Referer': 'https://movie.douban.com/tag/',
    # 'User-Agent': "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; de) Opera 11.01",
}

# conn = pymysql.connect(host='127.0.0.1', user='dbmovie', password='dbmovie', port=3306, db='Dbmovie', charset='utf8')
conn = pymysql.connect(host='127.0.0.1', user='root', password='jinxiu54321', port=3306, db='douban_movie_test01', charset="utf8")
cur = conn.cursor()

# http://api.douban.com/v2/movie/top250?start=0&count=25
# http://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=${event.start}&count=${event.count}
# https://api.douban.com/v2/movie/subject/27074316?apikey={your_api_key}


for start in range(1300000, 27150000, 1):
    url = 'http://api.douban.com/v2/movie/subject/{}?apikey=0df993c66c0c636e29ecbb5344252a4a'.format(start)

    try:
        res = requests.get(url, headers=header).json()
        lr = len(res)
        print(start, lr, res)
        if lr < 5:
            cur.execute("INSERT INTO db_invalidid (invalid_id) VALUES ({});".format(start))
            print("无效的id ", start)
        else:
            rating = res['rating']['average']  # 平均评分(四舍五入的平均法9.66) 9.7
            print(rating)
            original_title = res['original_title']  # 初始电影名 The Shawshank Redemption
            movie_image = res['images'][
                'small']  # 电影海报url(small,large,medium are the same)  http://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg
            year = ','.join(res['year'])  # 播出年份 1994年
            # alt = res['alt']  # 电影链接 https://movie.douban.com/subject/1292052/
            movie_id = res['id']  # 电影id  1292052
            title = res['title']  # 电影名  肖申克的救赎
            print(title)
            languages_list = ','.join(res['languages'])  # 电影语言  英语

            writers_list = res['writers']  # 编剧
            writers_list_len = len(writers_list)  # 编剧的人数
            writer_id_list = []  # 这部电影中编剧的id列表
            for wi in range(writers_list_len):
                writer_avatars = writers_list[wi]['avatars'][
                    'small']  # 编剧照片(small,large,medium are the same )  http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.jpg
                writer_name_en = writers_list[wi]['name_en']  # 编剧英文名 Frank Darabont
                writer_name = writers_list[wi]['name']  # 编剧名字  弗兰克·德拉邦特
                writer_alt = writers_list[wi]['alt']  # 编剧在豆瓣中的个人主页url  https://movie.douban.com/celebrity/1047973/
                writer_id = int(writers_list[wi]['id'])  # 编剧在豆瓣中个人id 1047973
                writer_id_list.append(
                    writer_id)  # 会创建一个编剧的表   我现在在考虑编剧的表和导演、演员是否放在一个表中，然后角色再用一个外键联合， 在电影表中只放这个编剧id的list列表

                insert_sql = "INSERT INTO db_writers (writer_id, writer_name_en, writer_name, writer_avatars, writer_alt) VALUES ({},'{}','{}','{}','{}');".format(
                    writer_id, writer_name_en, writer_name, writer_avatars, writer_alt)
                # print(insert_sql)
                cur.execute(insert_sql)

            pubdates_list = ','.join(res['pubdates'])  # 首播时间  ['1994-09-10(多伦多电影节)', '1994-10-14(美国)']

            tags_list = ','.join(res['tags'])  # 电影标签 ['经典', '励志', '信念', '自由', '人性', '人生', '美国', '希望', '剧情', '成长']

            durations_list = ','.join(res['durations'])  # 电影时长 ['142分钟']
            genres_list = ','.join(res['genres'])  # 电影类型 ['犯罪', '剧情']

            casts_list = res['casts']  # 电影主演
            casts_list_len = len(casts_list)  # 电影主演人数
            cast_id_list = []
            for ci in range(casts_list_len):
                cast_avatars = casts_list[ci]['avatars'][
                    'small']  # 主演照片(small,large,medium are the same ) http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.jpg
                cast_name_en = casts_list[ci]['name_en']  # 主演英文名 Tim Robbins
                cast_name = casts_list[ci]['name']  # 主演名字 蒂姆·罗宾斯
                cast_alt = casts_list[ci]['alt']  # 主演在豆瓣中的个人主页url https://movie.douban.com/celebrity/1054521/
                cast_id = casts_list[ci]['id']  # 主演在豆瓣中个人id 1054521
                cast_id_list.append(int(cast_id))  # 这部电影主演id列表
                insert_sql = "INSERT INTO db_casts (cast_id, cast_name_en, cast_name, cast_avatars, cast_alt) VALUES ({},'{}','{}','{}','{}');".format(
                    cast_id, cast_name_en, cast_name, cast_avatars, cast_alt)
                # print(insert_sql)
                cur.execute(insert_sql)

            countries_list = ','.join(res['countries'])  # 制片国家地区 ['美国']

            directors_list = res['directors']  # 导演
            directors_list_len = len(directors_list)  # 这部电影导演的人数
            director_id_list = []
            for di in range(directors_list_len):
                director_avatars = directors_list[di]['avatars'][
                    'small']  # 导演照片(small,large,medium are the same ) http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.jpg
                director_name_en = directors_list[di]['name_en']  # 导演英文名 Frank Darabont
                director_name = directors_list[di]['name']  # 导演名字 弗兰克·德拉邦特
                director_alt = directors_list[di]['alt']  # 导演在豆瓣中的个人主页url https://movie.douban.com/celebrity/1047973/
                director_id = directors_list[di]['id']  # 导演在豆瓣中个人id 1047973
                director_id_list.append(int(director_id))  # 这部电影导演id列表

                insert_sql = "INSERT INTO db_directors (director_id, director_name_en, director_name, director_avatars, director_alt) VALUES ({},'{}','{}','{}','{}');".format(
                    director_id, director_name_en, director_name, director_avatars, director_alt)
                # print(insert_sql)
                cur.execute(insert_sql)

            ratings_count = res['ratings_count']  # 参与评分的人数（此参数文档中无说明，根据词意翻译的）
            aka_list = ','.join(res['aka'])  # 电影别名 ['月黑高飞(港)', '刺激1995(台)', '地狱诺言', '铁窗岁月', '消香克的救赎']

            insert_sql = "INSERT INTO db_movie (movie_id, rating, ratings_count, title, original_title, aka_list, durations_list, year, pubdates_list, movie_image, languages_list, countries_list, writers_list_len, writer_id_list, casts_list_len, cast_id_list, directors_list_len, director_id_list, tags_list, genres_list) VALUES ({},{},{},'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}');".format(
                movie_id, rating, ratings_count, title, original_title, aka_list, durations_list, year, pubdates_list,
                movie_image, languages_list, countries_list, writers_list_len, writer_id_list, casts_list_len,
                cast_id_list, directors_list_len, director_id_list, tags_list, genres_list)
            print(insert_sql)
            cur.execute(insert_sql)

    except Exception as ex:
        print(ex)
    conn.commit()
    print(datetime.datetime.now())
    # sleep(random.uniform(0.5, 1))

cur.close()
conn.close()