# coding:utf-8
import tornado.ioloop
import tornado.web
import tornado.gen
import sys

#reload(sys)
#sys.setdefaultencoding('utf8')

from touclick import TouclickLib

pub_key = "" #公钥
pri_key = "" #私钥


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.render("static/login.html",)

class VerifyHandler(tornado.web.RequestHandler):
    def post(self):
        tc = TouclickLib(pub_key, pri_key)
        check_address = self.get_argument(tc.CHECK_ADDRESS, "")
        token = self.get_argument(tc.TOKEN, "")
        sid = self.get_argument(tc.SID, "")
        code, check_code, msg = tc.check(check_address, sid, token)
        if code == 0:
            #执行自己的程序逻辑
            #tc.callback(check_address, sid, token, True)
            pass
        self.write("code:" + str(code) + ", ckCode:" + str(check_code) + ", message:" + str(msg))

if __name__ == "__main__":
    app = tornado.web.Application([
                                      (r"/", MainHandler),
                                      (r"/verify", VerifyHandler)
                                  ], debug=True)
    app.listen(8088)
    tornado.ioloop.IOLoop.instance().start()
