#!coding:utf8
import sys

try:
    from setuptools import setup
except:
    from distutils.core import setup
VERSION = "1.0.0"

if __name__ == "__main__":
    with open('requirements.txt') as f:
        required = f.read().splitlines()
    setup(
        name="touclick",
        version=VERSION,
        packages=['captcha-python-sdk'],
        url='',
        license='',
        author='touclick',
        author_email='',
        description='Touclick Python SDK',
        install_requires=required,
    	)
