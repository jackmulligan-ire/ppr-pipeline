import React, { Dispatch, SetStateAction } from "react";

const SetSelectedLocationsContext = React.createContext<
  Dispatch<SetStateAction<string[]>> | undefined
>(undefined);

export default SetSelectedLocationsContext;
