import * as d3 from "d3";

window.renderChart = function(data){
  // Declare the chart dimensions and margins.
  const width = 500;
  const height = 300;
  const marginTop = 30;
  const marginRight = 0;
  const marginBottom = 30;
  const marginLeft = 40;

  // Declare the x (horizontal position) scale.
  const x = d3.scaleBand()
    .domain(data.map(d => d.STUDENTNAME)) // descending HIGHESTSCORE
    .range([marginLeft, width - marginRight])
    .padding(0.1);

  // Declare the y (vertical position) scale.
  const y = d3.scaleLinear()
    .domain([0, d3.max(data, (d) => d.HIGHESTSCORE)])
    .range([height - marginBottom, marginTop]);

  // Create the SVG container.
  const svg = d3.create("svg")
    .attr("width", width)
    .attr("height", height)
    .attr("viewBox", [0, 0, width, height])
    .attr("style", "max-width: 100%; height: auto;");

  d3.select("#chart").append(() => svg.node());

  // Add a rect for each bar.
  svg.append("g")
    .attr("fill", "steelblue")
    .selectAll()
    .data(data)
    .join("rect")
    .attr("x", (d) => x(d.STUDENTNAME))
    .attr("y", (d) => y(d.HIGHESTSCORE))
    .attr("height", (d) => y(0) - y(d.HIGHESTSCORE))
    .attr("width", x.bandwidth());

  // Add the x-axis and label.
  svg.append("g")
    .attr("transform", `translate(0,${height - marginBottom})`)
    .call(d3.axisBottom(x).tickSizeOuter(0));

  // Add the y-axis and label, and remove the domain line.
  svg.append("g")
    .attr("transform", `translate(${marginLeft},0)`)
    .call(d3.axisLeft(y).tickFormat((y) => (y).toFixed()))
    .call(g => g.select(".domain").remove())
    .call(g => g.append("text")
      .attr("x", -marginLeft)
      .attr("y", 10)
      .attr("fill", "currentColor")
      .attr("text-anchor", "start")
      .text("↑ HIGHESTSCORE (%)"));
}
