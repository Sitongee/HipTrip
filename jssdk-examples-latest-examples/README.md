TomTom JavaScript SDK ${project.version} Examples
==============

Documentation
--------------

Please refer to *http://developer.tomtom.com* for detailed documentation with examples.
Also latest version of the SDK can be found there.

Getting started
--------------

Before being able to check out examples you need to provide proper API keys for services like search, maps, etc.
In the directory where you extracted an archive run following bash commands:
find . -type f -exec sed -i 's/${api.key}/<your-maps-key>/g' {} \;
find . -type f -exec sed -i 's/${api.key.search}/<your-search-key>/g' {} \;
find . -type f -exec sed -i 's/${api.key.routing}/<your-routing-key>/g' {} \;
find . -type f -exec sed -i 's/${api.key.traffic}/<your-traffic-key>/g' {} \;
find . -type f -exec sed -i 's/${api.key.trafficFlow}/<your-traffic-flow-key>/g' {} \;

To run examples on you local machine you need to install [node.js](https://nodejs.org)
Then run command `npm install && npm start`.

License
--------------

© 1992 – ${currentYear} TomTom
The library is licensed under Apache License Version 2.0, check LICENSE.txt for details.
