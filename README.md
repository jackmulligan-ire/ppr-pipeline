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

## Using Altas

## Roadmap

- [ ]
- [ ]
- [ ]

## Acknowledgements

- Icon credit: https://github.com/alexandresanlim/Badges4-README.md-Profile
- README template credit: https://github.com/othneildrew/Best-README-Template
