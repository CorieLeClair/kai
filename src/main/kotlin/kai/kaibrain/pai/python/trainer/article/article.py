from textblob import TextBlob
from newspaper import Article
import json
import nltk
import sys


def train_article_online(url):
    article = Article(url)

    # NLP systems
    article.download()
    article.parse()
    article.nlp()

    article_summary = article.summary
    article_obj = TextBlob(article_summary)
    article_sent = article_obj.sentiment.polarity

    #print(article.title, article_summary)
    return article_sent

def article_title():
    title = ""
    return title
