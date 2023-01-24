import { useContext } from "react";
import HighchartsReact from "highcharts-react-official";
import * as Highcharts from "highcharts";
import SelectedLocationsContext from "../../contexts/SelectedLocationsContext";
import DataContext from "../../contexts/DataContext";
import { SequentialData } from "../../contexts/DataContext/DataContext";
import ActiveMetricContext from "../../contexts/ActiveMetricContext";

function ChartDisplay() {
  const selectedLocations = useContext(SelectedLocationsContext);
  const data = useContext(DataContext);
  const activeMetric = useContext(ActiveMetricContext);

  const getSeries = (
    inputData: { location: string; data: SequentialData[] }[],
    activeMetric: { label: string; value: string }
  ): Highcharts.SeriesOptionsType[] => {
    const getProcessedData = (rawData: SequentialData[]) => {
      return rawData.map((data) => [
        Date.UTC(data.year, data.month, 1),
        data[activeMetric.value as keyof SequentialData],
      ]);
    };

    return inputData
      .filter((child) => selectedLocations.includes(child.location))
      .map((child) => {
        return {
          type: "line",
          data: getProcessedData(child.data),
          name: child.location,
        };
      });
  };

  const options: Highcharts.Options = {
    series: activeMetric ? getSeries(data, activeMetric) : undefined,
    title: {
      text: undefined,
    },
    xAxis: {
      type: "datetime",
      title: {
        text: "Year",
      },
      tickInterval: 365 * 24 * 3600 * 1000,
    },
    yAxis: {
      title: {
        text: activeMetric?.label,
      },
    },
    tooltip: {
      pointFormat: "<b>{series.name}: {point.y}<b>",
      xDateFormat: "%b %Y",
    },
  };

  return <HighchartsReact highcharts={Highcharts} options={options} />;
}

export default ChartDisplay;
