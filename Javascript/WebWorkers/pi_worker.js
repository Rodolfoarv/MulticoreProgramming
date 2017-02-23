'use strict';

onmessage = (event) => {
  const numRects = event.data.numRects;
  const start = event.data.start;
  const end = event.data.end;
  let sum = 0;
  const width = 1 / numRects;
  for (let i = start; i < end; i++){
    let mid = (i + 0.5 ) * width;
    let height = 4.0 / (1.0 + mid * mid);
    sum += height;
  };
  postMessage(sum);
}
