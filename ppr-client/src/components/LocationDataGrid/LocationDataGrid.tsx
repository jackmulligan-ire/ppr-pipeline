import { GridColDef, GridRowsProp, DataGrid } from "@mui/x-data-grid";
import { useContext } from "react";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";

const columns: GridColDef[] = [
  { field: "location", headerName: "Location", width: 200 },
  { field: "data", headerName: "Data", width: 125 },
];

function LocationDataGrid() {
  const selectedLocations = useContext(SelectedLocationsContext);

  const rows: GridRowsProp = [
    {
      id: 1,
      location: selectedLocations[0],
      data: "300k",
    },
  ];

  return <DataGrid columns={columns} rows={rows} autoHeight />;
}

export default LocationDataGrid;
