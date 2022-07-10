FROM ubuntu:latest

RUN pip install boto3

COPY aws_credentials .aws/credentials
COPY ppr_pipleine/ ppr_pipleine/

ENTRYPOINT ["python", "-m ppr_pipleine"]