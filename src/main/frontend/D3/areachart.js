import * as d3 from 'd3';

window.areaChart = function (data) {
  // Dimensiones y márgenes del gráfico
  const width = 500;
  const height = 300;
  const marginTop = 20;
  const marginRight = 30;
  const marginBottom = 50; // Espacio para etiquetas de nombres
  const marginLeft = 40;

  const svg = d3.create("svg")
    .attr("width", width)
    .attr("height", height)
    .attr("viewBox", [0, 0, width, height])
    .attr("style", "max-width: 100%; height: auto;");

  d3.select("#chart").append(() => svg.node());

  // Escala x: nombres de estudiantes
  const x = d3.scalePoint()
    .domain(data.map(d => d.STUDENTNAME)) // Mapea nombres de estudiantes
    .range([marginLeft, width - marginRight])
    .padding(0.5); // Espaciado entre puntos

  // Escala y: notas más altas
  const y = d3.scaleLinear()
    .domain([0, d3.max(data, d => d.HIGHESTSCORE)]) // Máximo en notas
    .range([height - marginBottom, marginTop]);

  // Generador de área
  const area = d3.area()
    .x(d => x(d.STUDENTNAME)) // Posición x por nombre
    .y0(y(0))                // Base del área en 0
    .y1(d => y(d.HIGHESTSCORE)); // Altura basada en la nota

  // Agregar área
  svg.append("path")
    .attr("fill", "steelblue")
    .attr("d", area(data));

  // Agregar eje X
  svg.append("g")
    .attr("transform", `translate(0,${height - marginBottom})`)
    .call(d3.axisBottom(x))
    .selectAll("text") // Ajustar nombres largos
    .attr("transform", "rotate(-45)")
    .style("text-anchor", "end");

  // Agregar eje Y
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
      .text("↑ Nota más alta"));
};