import * as d3 from 'd3';

window.renderChart = function(data) {
  // Dimensiones y márgenes del gráfico
  const margin = { top: 20, right: 20, bottom: 50, left: 50 };
  const width = 500 - margin.left - margin.right;
  const height = 300 - margin.top - margin.bottom;

  // Crear el contenedor SVG
  const svg = d3.select("#chart")
    .html('') // Limpiar contenido previo
    .append('svg')
    .attr('width', width + margin.left + margin.right)
    .attr('height', height + margin.top + margin.bottom)
    .append('g')
    .attr('transform', `translate(${margin.left},${margin.top})`);

  // Escalas
  const x = d3.scaleBand()
    .domain(data.map(d => d.STUDENTNAME)) // Usar nombres como etiquetas
    .range([0, width])
    .padding(0.1); // Espaciado entre barras

  const y = d3.scaleLinear()
    .domain([0, d3.max(data, d => d.HIGHESTSCORE)]) // Escalar en base al valor más alto
    .range([height, 0]);

  // Ejes
  svg.append('g')
    .attr('transform', `translate(0,${height})`)
    .call(d3.axisBottom(x)) // Eje X
    .selectAll('text') // Rotar etiquetas si son largas
    .attr('transform', 'rotate(-45)')
    .style('text-anchor', 'end');

  svg.append('g')
    .call(d3.axisLeft(y)); // Eje Y

  // Dibujar barras
  svg.selectAll('.bar')
    .data(data)
    .enter()
    .append('rect')
    .attr('x', d => x(d.STUDENTNAME))
    .attr('y', d => y(d.HIGHESTSCORE))
    .attr('width', x.bandwidth())
    .attr('height', d => height - y(d.HIGHESTSCORE))
    .attr('fill', 'steelblue');
};
