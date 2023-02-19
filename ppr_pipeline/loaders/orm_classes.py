from sqlalchemy import Column, Text, Integer, DateTime
from sqlalchemy.ext.declarative import declarative_base

base = declarative_base()

class Property_Transaction_Staging(base):
    __tablename__ = 'staging'

    address = Column(Text, primary_key=True)
    county = Column(Text, nullable=False)
    eircode = Column(Text)
    price = Column(Text, primary_key=True)
    not_full_market_price = Column(Text, nullable=False)
    vat_exclusive = Column(Text, nullable=False)
    description_of_property = Column(Text) 
    property_size_description = Column(Text)
    date_of_sale = Column(Text, primary_key=True)

class Last_Updated(base):
    __tablename__ = 'last_updated'

    id = Column(Integer, primary_key=True)
    update = Column(DateTime, primary_key=True)