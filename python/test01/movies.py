# 获取豆瓣近期电影数据
import datetime
import uuid

import pymysql as pymysql
# from pip._vendor import requests
# from pip._vendor.requests import RequestException
import requests
from requests import RequestException


# def douBanSplider():
#   url = 'http://api.douban.com/v2/movie/in_theaters';
#   try:
#     reponse = requests.get(url);
#     if reponse.status_code == 200:
#       list = eval(reponse.text.encode('utf-8'))
#       return list.get('subjects')
#       return None
#     except RequestException as e:
#       print('请求索引页出错')
#       return None
def main():
    html = douBanSplider()
    conn = connectMysql()
    movie_list = []
    avatar_list = []
    directors_list = []
    movie_avatar_list = []
    genres_list = []
    for x in html:
        print(type(x))
        print(x)
        mv_id = x.get('id')
        title = x.get('title')
        original_title = x.get('original_title')
        subtype = x.get('subtype')
        collect_count = x.get('collect_count')
        create_date = datetime.datetime.now()
        directors = x.get('directors')
        year = x.get('year')

        genres = x.get('genres')
        genres_str = ','.join(genres)
        # genres_str = genres.join(',')
        for i in range(0, len(genres)):
            genres_data = (str(uuid.uuid4()), genres[i])
            genres_list.append(genres_data)

        rating = x.get('rating')
        average = rating.get('average')
        stars = rating.get('stars')
        max_grade = rating.get('max')
        min_grade = rating.get('min')
        # 将电影信息存入数据库
        movie_data = (mv_id, title, original_title, year, subtype, directors[0].get('id'),
                      genres_str, average, max_grade, min_grade, stars, collect_count, create_date)
        directors_data = (directors[0].get('id'), directors[0].get('name'),
                          directors[0].get('alt').replace('\\', ''),
                          directors[0].get('avatars').get('small').replace('\\', ''),
                          directors[0].get('avatars').get('medium').replace('\\', ''),
                          directors[0].get('avatars').get('large').replace('\\', ''))
        movie_list.append(movie_data)
        directors_list.append(directors_data)

        casts = x.get('casts')
        for avatar in casts:
            avatar_id = avatar.get('id')
            avatar_name = avatar.get('name')
            avatar_alt = avatar.get('alt').replace('\\', '')
            avatar_image_small = avatar.get('avatars').get('small').replace('\\', '')
            avatar_image_medium = avatar.get('avatars').get('medium').replace('\\', '')
            avatar_image_large = avatar.get('avatars').get('large').replace('\\', '')
            avatar_data = (
            avatar_id, avatar_name, avatar_alt, avatar_image_small, avatar_image_medium, avatar_image_large)
            movie_avatar_data = (mv_id, avatar_id)
            avatar_list.append(avatar_data)
            movie_avatar_list.append(movie_avatar_data)
    insertMovie(conn, movie_list)
    insertDirectors(conn, directors_list)
    insertAvatar(conn, avatar_list)
    insertGenres(conn, genres_list)
    insertMovieAvatars(conn, movie_avatar_list)
    closeMysql(conn)


# 连接数据库
def connectMysql():
    try:
        conn = pymysql.connect(host='127.0.0.1', user='root', password='jinxiu54321', db='douban_movie_test01',
                               charset="utf8")
        print("成功连接数据库！")
        return conn
    except Exception as e:
        print("ERROR：" + e)


def closeMysql(conn):
    conn.close();
    print("关闭数据库！")


# 向数据库添加电影信息
def insertMovie(conn, list):
    cursor = conn.cursor()
    sql = "replace into mv_movie(id,title,original_title,year,subtype,directors,genres,average,max,min,stars,collect_count,create_date) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"

    try:
        cursor.executemany(sql, list)
        print("插入mv_movie" + str(cursor.rowcount) + "条数据")
        conn.commit()
    except Exception as e:
        print("插入mv_movie数据库错误=" + e)
        conn.rollback()


# 向数据库添加演员
def insertAvatar(conn, list):
    cursor = conn.cursor()
    sql = "replace into mv_avatar(id,name,alt,image_small,image_medium,image_large) values(%s,%s,%s,%s,%s,%s)"

    try:
        cursor.executemany(sql, list)
        print("插入mv_avatar" + str(cursor.rowcount) + "条数据")
        conn.commit()
    except Exception as e:
        print("插入mv_avatar数据库错误=" + e)
        conn.rollback()


# 向数据库添加导演
def insertDirectors(conn, list):
    cursor = conn.cursor()
    sql = "replace into mv_director(id,name,alt,image_small,image_medium,image_large) values(%s,%s,%s,%s,%s,%s)"

    try:
        cursor.executemany(sql, list)
        print("插入mv_director" + str(cursor.rowcount) + "条数据")
        conn.commit()
    except Exception as e:
        print("插入mv_director数据库错误=" + e)
        conn.rollback()


# 向数据库添加电影演员关联数据
def insertMovieAvatars(conn, list):
    cursor = conn.cursor()
    sql = "replace into mv_movie_avatar_ids(movie_id,avatar_id) values(%s,%s)"

    try:
        cursor.executemany(sql, list)
        print("插入mv_movie_avatar_ids" + str(cursor.rowcount) + "条数据")
        conn.commit()
    except Exception as e:
        print("插入mv_movie_avatar_ids数据库错误=" + e)
        conn.rollback()


# 向数据库添加电影类型
def insertGenres(conn, list):
    cursor = conn.cursor()
    sql = "replace into mv_genres(id,genres) values(%s,%s)"
    try:
        cursor.executemany(sql, list)
        print("插入mv_genres" + str(cursor.rowcount) + "条数据")
        conn.commit()
    except Exception as e:
        print("插入mv_genres数据库错误=" + e)
        conn.rollback()


# 获取豆瓣近期电影数据
def douBanSplider():
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
    }
    # url = 'http://api.douban.com/v2/movie/in_theaters'
    url = 'https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=100&page_start=0'
    try:
        reponse = requests.get(url, headers=headers)
        if reponse.status_code == 200:
            print("请求成功")
            # list = eval(reponse.text.encode('utf-8'))
            # reponse.encoding = 'utf-8'
            list = reponse.json()
            print(list)

            # return list.get('subjects')
            return list['subjects']
        else:
            print("请求失败， status_code:" + str(reponse.status_code))
        # return None
        return []
    except RequestException as e:
        print('请求索引页出错')
        # return None
        return []


main()
