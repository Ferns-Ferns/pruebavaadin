import * as d3 from '../d3.min.js';

window.areaChart = function(data){

  // Declare the chart dimensions and margins.
  const width = 500;
  const height = 300;
  const marginTop = 20;
  const marginRight = 30;
  const marginBottom = 30;
  const marginLeft = 40;

  // Declare the x (horizontal position) scale.
  const x = d3.scalePoint(data.map(d => d.STUDENTNAME), [marginLeft, width - marginRight]);

  // Declare the y (vertical position) scale.
  const y = d3.scaleLinear([0, d3.max(data, d => d.HIGHESTSCORE)], [height - marginBottom, marginTop]);

  // Declare the area generator.
  const area = d3.area()
    .x(d => x(d.STUDENTNAME))
    .y0(y(0))
    .y1(d => y(d.HIGHESTSCORE));

  // Create the SVG container.
  const svg = d3.create("svg")
    .attr("width", width)
    .attr("height", height)
    .attr("viewBox", [0, 0, width, height])
    .attr("style", "max-width: 100%; height: auto;");

  d3.select("#chart").append(() => svg.node());

  // Append a path for the area (under the axes).
  svg.append("path")
    .attr("fill", "steelblue")
    .attr("d", area(data));

  // Add the x-axis.
  svg.append("g")
    .attr("transform", `translate(0,${height - marginBottom})`)
    .call(d3.axisBottom(x).ticks(width / 80).tickSizeOuter(0));

  // Add the y-axis, remove the domain line, add grid lines and a label.
  svg.append("g")
    .attr("transform", `translate(${marginLeft},0)`)
    .call(d3.axisLeft(y).ticks(height / 40))
    .call(g => g.select(".domain").remove())
    .call(g => g.selectAll(".tick line").clone()
      .attr("x2", width - marginLeft - marginRight)
      .attr("stroke-opacity", 0.1))
    .call(g => g.append("text")
      .attr("x", -marginLeft)
      .attr("y", 10)
      .attr("fill", "currentColor")
      .attr("text-anchor", "start")
      .text("↑ Daily HIGHESTSCORE ($)"));
}