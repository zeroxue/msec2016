## msec2016_backend

Backend of [msec2016 project](https://miaodx.github.io/msec2016/).

## Reference

1.[A Fraction calc lib](https://github.com/MightyPork/rcalc)

2.[Generating String that match a given regular expression](https://github.com/mifmif/Generex)


## See `Notes.md` for changes to the `rcalc` library.

## Update 20160926

`/newProblems/{num}/{minOpNum}/{maxOpNum}/{datatype}/{optype}`,算是一个比较稳定的 API 了，其中 `num` 是题目数量，`minOpNum` 是出现的运算符最小个数，`maxOpNum` 是出现的运算符最多个数，`datatype` 表示 `int+fraction+mixed_fraction`，`optype` 表示 `+-*#`.


For example,visit [http://demo.tjuwork.win:9000/newProblems/3/1/1/111/1111](http://demo.tjuwork.win:9000/newProblems/3/1/1/111/1111),change the numbers and have a try.


