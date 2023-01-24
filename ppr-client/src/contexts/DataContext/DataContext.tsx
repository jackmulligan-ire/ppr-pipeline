import React from "react";

export type SequentialData = {
  year: number;
  month: number;
  medianSalePrice: number;
  transactions: number;
};

const DataContext = React.createContext<
  { location: string; data: SequentialData[] }[]
>([]);

export default DataContext;
