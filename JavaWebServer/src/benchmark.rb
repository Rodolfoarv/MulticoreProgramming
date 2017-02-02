#!/usr/bin/env ruby

N = 10
pids = []

N.times do
  pids << spawn('curl http://localhost:12345')
end

pids.each do |x|
  Process.wait x
end
