import { useContext } from "react";
import SetSelectedLocationsContext from "../contexts/SelectedLocationsContext/SetSelectedLocationsContext";

function useSetSelectedLocations() {
  const setSelectedLocations = useContext(SetSelectedLocationsContext);

  if (!setSelectedLocations)
    throw new Error("<SetSelectedLocationsContext.Provider is undefined");

  return setSelectedLocations;
}

export default useSetSelectedLocations;
