FROM ubuntu:latest

RUN pip install boto3

COPY ppr_pipleine/ ppr_pipleine/
