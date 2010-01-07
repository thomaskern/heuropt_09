m = 13
n = 20

c = 0
m.times do |t|
  c += n - 1 - t
end

(m-1).times do |t|
  c -= (t + 1)
end

puts c