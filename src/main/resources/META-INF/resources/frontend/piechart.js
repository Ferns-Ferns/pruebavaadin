// import * as d3 from "d3";
//
// window.renderChart2 = function (data) {
//   console.log("Datos recibidos:", data);
//
//   // Dimensiones y márgenes del gráfico
//   const width = 400; // Ancho del SVG
//   const height = 400; // Alto del SVG
//   const radius = Math.min(width, height) / 2; // Radio del gráfico
//
//   // Seleccionar el contenedor SVG
//   const chartDiv = d3.select("#chart");
//   console.log("SVG seleccionado:", chartDiv.node());
//
//   // Limpiar cualquier contenido previo
//   chartDiv.html("");
//   console.log("Contenido previo del SVG eliminado.");
//
//   // Crear el contenedor SVG
//   const svg = chartDiv
//     .append("svg")
//     .attr("width", width)
//     .attr("height", height)
//     .append("g")
//     .attr("transform", `translate(${width / 2}, ${height / 2})`);
//   console.log("SVG creado:", svg.node());
//
//   // Crear una escala de colores
//   const color = d3.scaleOrdinal()
//     .domain(data.map(d => d.STUDENTNAME))
//     .range(d3.schemeCategory10); // Colores predefinidos
//
//   // Crear un generador de gráficos de pastel
//   const pie = d3.pie()
//     .value(d => d.HIGHESTSCORE); // Basar el tamaño del segmento en la nota
//   const pieData = pie(data);
//   console.log("Datos procesados para el gráfico de pie:", pieData);
//
//   // Crear un generador de arcos
//   const arc = d3.arc()
//     .innerRadius(0) // Gráfico sólido
//     .outerRadius(radius);
//
//   // Dibujar los segmentos del gráfico
//   svg.selectAll("path")
//     .data(pieData)
//     .enter()
//     .append("path")
//     .attr("d", arc)
//     .attr("fill", d => color(d.data.STUDENTNAME)) // Color según el estudiante
//     .attr("stroke", "white")
//     .style("stroke-width", "2px");
//
//   // Agregar etiquetas a cada segmento
//   svg.selectAll("text")
//     .data(pieData)
//     .enter()
//     .append("text")
//     .text(d => `${d.data.STUDENTNAME}: ${d.data.HIGHESTSCORE}`)
//     .attr("transform", d => `translate(${arc.centroid(d)})`)
//     .style("text-anchor", "middle")
//     .style("font-size", "12px");
//   console.log("Gráfico de pie creado.");
// };