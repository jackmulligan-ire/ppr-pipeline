<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/jackmulligan-ire/ppr-pipeline">
    <img src="readme-images/pprhouse-logo.png" alt="PPR House Logo" height=250 width=250>
  </a>

<h3 align="center">PPR House</h3>

  <p align="center">
   The Irish Property Price Register in a data warehouse.
  </p>
</div>

## **About PPR House**

PPR House is the Irish property price register scraped from its source and loaded into a data warehouse. The files are queried using Python’s request library then stored in Amazon S3. From here, they are retrieved and then lightly transformed then loaded into a PostgreSQL database. Here, they are initially loaded into a staging table before transformation scripts are run to load them into a warehouse schema of 3 dimension tables and 1 fact table. Compared to 3NF databases designed for CRUD transactions, a data warehouse is structured specifically for performing data analysis queries like joins and aggregations.

The primary motivation behind this project is to allow people to more easily perform data analysis on the Property Price Register, as this is currently only available in the form of .xlsx files, with Excel struggling to handle a dataset of 450,000 transactions. A SQL database is far more adept at handling data of this size. A secondary motivation making public dashboards so that developments in the Property Price Register can be monitored. Being a direct source of truth about the Irish property market, it should be possible to track price movements in the property market on a weekly basis. This will serve as a compliment to reports such as the Daft House Price Report, which are typically published on a quarterly or annual basis.

V1 of the project will consist of:

- A data warehouse.
- A Python package to query the latest entries on the Property Price Register on a weekly basis and inject the latest sales into the warehouse (running on the basis of an EtLT pipeline).
- A server that will provide endpoints for users to run their own SQL queries on the warehouse.
- A frontend that will present dashboards of the latest movements in the Irish property market.

### Built With

![python](https://img.shields.io/badge/Python-FFD43B?style=for-the-badge&logo=python&logoColor=blue)
![amazon aws](https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![postgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![plsql](https://img.shields.io/badge/PLSQL-F80000?style=for-the-badge&logo=oracle&logoColor=black)

## Getting Started

You will need to clone the repo locally to launch the data warehouse.

### To stand-up the data warehouse using Docker.

The data warehouse can easily be stood up using the docker-compose.yml file. To do so, simply do the following:

- Download and [install Docker](https://docs.docker.com/engine/install/ubuntu/) (Linux) / [install Docker Desktop](https://docs.docker.com/get-docker/) (Cross-Platform)
- To stand up the database: run `docker-compose up`
- To also stand up a PGAdmin container: run `docker-compose --profile local up`

You can now use your PostgresSQL Dashboard to query the database.

### To stand-up the data warehouse using backup.sql

The sql directory contains a backup.sql which can be mounted into your SQL platform. Note that it must be compatible with plsql for the backup.sql file to run properly.

### Creating the warehouse from scratch.

It's also possible to query the website of the Property Price Register and create the warehouse from scratch. To do this, perform the following steps:

- Set up an account on Amazon Web Services and create an S3 Bucket. This is expected to be called "ppr-pipeline" but this can be renamed as desired by changing the `BUCKET_NAME` constant defined in `ppr_pipeline/scrapers/**init**.py`.
- [Generate credentials](https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html) to connect to your AWS on your local machine.
- Save the credentials generated to a `~/.aws/credentials` file. This should contain `aws_access_key_id` and `aws_secret_access_key` fields.
- Navigate into your copy of the repo and run `mkdir tmp logs`. These directories are required to convert data and log errors but are listed under `.gitignore` to prevent unnecessary data from being uploaded.
- Stand up the database using the `backup-clean.sql` file. This can also be done using `docker-compose`.yml by changing the reference in `volumes` from `backup-sql` to `backup-clean.sql` This will generate empty data warehouse tables.
- Run `python -m ppr_pipeline scrape_all` to query the latest data from the Property Price Register and inject the latest data into your S3 bucket.
- Run `python -m ppr_pipeline inject_all` to query the S3 bucket and inject the data there into your SQL database.
- Finally, open a SQL dashboard and run all non-backup files in the sql folder as queries. This will provide the necessary functions required for transformation and transform the data in staging into the 4 data warehouse tables.

## Using Altas

## Roadmap

- [ ]
- [ ]
- [ ]

## Acknowledgements

- Icon credit: https://github.com/alexandresanlim/Badges4-README.md-Profile
- README template credit: https://github.com/othneildrew/Best-README-Template
