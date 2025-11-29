export const config = { runtime: "edge" };

const CSV_URL =
  "https://onlqrhfjvwm2vstl.public.blob.vercel-storage.com/city_temperature.csv";

function parseLine(arr) {
  return {
    region: arr[0],
    country: arr[1],
    state: arr[2],
    city: arr[3],
    month: Number(arr[4]),
    day: Number(arr[5]),
    year: Number(arr[6]),
    avg: Number(arr[7]),
  };
}

function matches(r, params) {
  if (params.region && r.region.toLowerCase() !== params.region.toLowerCase())
    return false;

  if (params.country && r.country.toLowerCase() !== params.country.toLowerCase())
    return false;

  if (params.state && r.state.toLowerCase() !== params.state.toLowerCase())
    return false;

  if (params.city && r.city.toLowerCase() !== params.city.toLowerCase())
    return false;

  if (params.startYear && r.year < Number(params.startYear)) return false;
  if (params.endYear && r.year > Number(params.endYear)) return false;

  return true;
}

function format(r) {
  return `${r.avg.toFixed(2)}°F on ${r.month}/${r.day}/${r.year} in ${r.city}, ${r.state}, ${r.country}`;
}

export default async function handler(req) {
  const url = new URL(req.url);
  const params = Object.fromEntries(url.searchParams.entries());

  const csv = await fetch(CSV_URL).then((r) => r.text());
  const lines = csv.trim().split("\n");
  lines.shift();

  const readings = lines.map((line) => parseLine(line.split(",")));

  const filtered = readings.filter(
    (r) => r.avg !== -99 && matches(r, params)
  );

  if (filtered.length === 0) {
    return new Response("No results found.");
  }

  const min = filtered.reduce((a, b) => (a.avg < b.avg ? a : b));
  const max = filtered.reduce((a, b) => (a.avg > b.avg ? a : b));

  let out = "";
  out += `Matches Found: ${filtered.length}\n\n`;
  out += `Minimum Temperature:\n${format(min)}\n\n`;
  out += `Maximum Temperature:\n${format(max)}\n`;

  return new Response(out);
}
