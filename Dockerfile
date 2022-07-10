FROM ubuntu:latest

RUN apt-get update && apt-get -y install pip python-is-python3
RUN pip install requests boto3

COPY aws_credentials root/.aws/credentials
COPY ppr_pipeline/ home/ppr_pipeline/

WORKDIR home/

ENTRYPOINT ["python", "-m" , "ppr_pipeline"]