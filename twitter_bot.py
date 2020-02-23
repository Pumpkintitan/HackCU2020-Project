import twitter

auth = open("twitterauth")

api = twitter.Api(consumer_key=auth.readline().strip(),
                  consumer_secret=auth.readline().strip(),
                  access_token_key=auth.readline().strip(),
                  access_token_secret=auth.readline().strip())

status = api.PostUpdate('Ninja still has no balls')
print(status.text)

