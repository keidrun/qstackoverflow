# qstackoverflow

[![tests][tests]][tests-url]
[![coverage][cover]][cover-url]

Stack Overflow's Search CLI.

## Install
```
$ curl -fsSL https://raw.githubusercontent.com/keidrun/qstackoverflow/master/install | bash
```

## Usage
```
usage: sos title [option...]
 -f,--fromdate <fromdate>   set a "fromdate" parameter, a format must be
                            "yyyy-MM-dd"
 -g,--tagged <tagged>       set a "tagged" parameter.
 -h,--help                  show help
 -l,--lang <lang>           access the language's site, "en"(default) or
                            "ja"
 -max,--maxdate <maxdate>   set a "max" date parameter, a format must be
                            "yyyy-MM-dd"
 -min,--mindate <mindate>   set a "min" date parameter, a format must be
                            "yyyy-MM-dd"
 -n,--notagged <notagged>   set a "notagged" parameter
 -o,--order <order>         set a "order" parameter, "desc"(default) or
                            "asc"
 -p,--page <page>           set a "page" parameter
 -r,--raw                   show raw json
 -s,--sort <sort>           set a "sort" parameter, "activity"(default) or
                            "votes" or "creation" or "relevance"
 -t,--todate <todate>       set a "todate" parameter, a format must be
                            "yyyy-MM-dd"
 -v,--verbose               show verbose
 -z,--pagesize <pagesize>   set a "pagesize" parameter
```

## Uninstall
```
$ cd qstackoverflow
$ ./uninstall
```

[tests]:https://travis-ci.org/keidrun/qstackoverflow.svg?branch=master
[tests-url]:https://travis-ci.org/keidrun/qstackoverflow

[cover]:https://codecov.io/gh/keidrun/qstackoverflow/branch/master/graph/badge.svg
[cover-url]:https://codecov.io/gh/keidrun/qstackoverflow

