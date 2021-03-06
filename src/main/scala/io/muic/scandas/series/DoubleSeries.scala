package io.muic.scandas.series
import Util._
class DoubleSeries(seq: Seq[Double]) extends NumericSeries{


  def sort_value(): DoubleSeries = new DoubleSeries(obj().sorted)

  def boolIdx(that: BoolSeries): DoubleSeries =
    new DoubleSeries(obj().zip(that.obj()).filter(x => x._2).map(x => x._1))

  def abs(): NumericSeries = new DoubleSeries(obj().map(x => x.abs))


  def mean(): Double = sum()/size()

  def median(): Double = {
    val (lower, upper) = seq.sortWith(_<_).splitAt(seq.size / 2)
    if (seq.size % 2 == 0) (lower.last + upper.head) / 2.0 else upper.head
  }

  /// dont have case of having two mode -> Seq(4.0, 4.0, 5.0 ,5.0)
  def mode(): Double = obj().toParArray.groupBy(x => x).maxBy(x => x._2.size)._1

  def min(): Double = obj().toParArray.min

  def max(): Double = obj().toParArray.max

  def sum(): Double = obj().toParArray.sum

  def argMax(): Int = obj().indexOf(max())

  def argMin(): Int = obj().indexOf(min())

  def /(that: DoubleSeries): DoubleSeries =
    new DoubleSeries(opsNum(obj(), that.obj(), _/_))
  def /(that: Double): DoubleSeries =
    new DoubleSeries(opsNumOne(obj(), that, _/_))

  // for int
  def /(that: IntSeries): DoubleSeries =
    new DoubleSeries(opsNumDI(obj(), that.obj(), _/_))
  def /(that: Int): DoubleSeries =
    new DoubleSeries(opsNumOneDI(obj(), that, _/_))

  def +(that: DoubleSeries): DoubleSeries =
    new DoubleSeries(opsNum(obj(), that.obj(), _+_))
  def +(that: Double): DoubleSeries =
    new DoubleSeries(opsNumOne(obj(), that, _+_))

  def +(that: IntSeries): DoubleSeries =
    new DoubleSeries(opsNumDI(obj(), that.obj(), _+_))
  def +(that: Int): DoubleSeries =
    new DoubleSeries(opsNumOneDI(obj(), that, _+_))


  def -(that: DoubleSeries): DoubleSeries =
    new DoubleSeries(opsNum(obj(), that.obj(), _-_))
  def -(that: Double): DoubleSeries =
    new DoubleSeries(opsNumOne(obj(), that, _-_))
  override def -(that: IntSeries): DoubleSeries =
    new DoubleSeries(opsNumDI(obj(), that.obj(), _-_))
  override def -(that: Int): DoubleSeries =
    new DoubleSeries(opsNumOneDI(obj(), that, _-_))


  def *(that: DoubleSeries): DoubleSeries =
    new DoubleSeries(opsNum(obj(), that.obj(), _*_))
  def *(that: Double): DoubleSeries =
    new DoubleSeries(opsNumOne(obj(), that, _*_))
  override def *(that: IntSeries): DoubleSeries =
    new DoubleSeries(opsNumDI(obj(), that.obj(), _*_))
  override def *(that: Int): DoubleSeries =
    new DoubleSeries(opsNumOneDI(obj(), that, _*_))

  def >(that: DoubleSeries): BoolSeries =
    new BoolSeries(compNum(obj(), that.obj(), _>_))
  def >(that: Double): BoolSeries =
    new BoolSeries(compNumOne(obj(), that, _>_))
  def >(that: IntSeries): BoolSeries =
    new BoolSeries(compNumDI(obj(), that.obj(), _>_))
  def >(that: Int): BoolSeries =
    new BoolSeries(compNumOneDI(obj(), that, _>_))

  def <=(that: DoubleSeries): BoolSeries =
    new BoolSeries(compNum(obj(), that.obj(), _<=_))
  def <=(that: Double): BoolSeries =
    new BoolSeries(compNumOne(obj(), that, _<=_))
  def <=(that: IntSeries): BoolSeries =
    new BoolSeries(compNumDI(obj(), that.obj(), _<=_))
  def <=(that: Int): BoolSeries =
    new BoolSeries(compNumOneDI(obj(), that, _<=_))

  def <(that: DoubleSeries): BoolSeries =
    new BoolSeries(compNum(obj(), that.obj(), _<_))
  def <(that: Double): BoolSeries =
    new BoolSeries(compNumOne(obj(), that, _<_))
  def <(that: IntSeries): BoolSeries =
    new BoolSeries(compNumDI(obj(), that.obj(), _<_))
  def <(that: Int): BoolSeries =
    new BoolSeries(compNumOneDI(obj(), that, _<_))

  def >=(that: DoubleSeries): BoolSeries =
    new BoolSeries(compNum(obj(), that.obj(), _>=_))
  def >=(that: Double): BoolSeries =
    new BoolSeries(compNumOne(obj(), that, _>=_))
  def >=(that: IntSeries): BoolSeries =
    new BoolSeries(compNumDI(obj(), that.obj(), _>=_))
  def >=(that: Int): BoolSeries =
    new BoolSeries(compNumOneDI(obj(), that, _>=_))

  def nonzero(): DoubleSeries = new DoubleSeries(obj().filter(x => x != 0))

  def obj(): Vector[Double] = seq.toVector

  def arloc(ii: Seq[Int]): DoubleSeries = new DoubleSeries(ii.map(i => obj()(i)))

  def std: Double = {
    val mu = mean()
    math.sqrt(obj().map(x => math.pow(x-mu,2)).sum/size())
  }
}
