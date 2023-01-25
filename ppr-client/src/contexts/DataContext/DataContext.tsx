import React from "react";

export type SequentialData = {
  year: number;
  month: number;
  medianSalePrice: number;
  transactions: number;
};

export type PropertyTransactions = {
  location: string;
  data: SequentialData[];
};

const DataContext = React.createContext<PropertyTransactions[]>([]);

export default DataContext;
