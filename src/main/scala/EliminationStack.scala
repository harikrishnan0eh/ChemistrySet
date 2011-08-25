// The Treiber stack with elimination-backoff, via reagents

package chemistry

final class EliminationStack[A] {
  private val stack = new TreiberStack[A]
  private val (elimPop, elimPush) = Chan[A]()

  val push: Reagent[A,Unit] = stack.push <+> elimPush
  val tryPop: Reagent[Unit,Option[A]] = stack.tryPop <+> elimPop.map(Some(_))
  val pop: Reagent[Unit,A] = stack.pop <+> elimPop

/*
  val push: Reagent[A,Unit] = elimPush <+> stack.push
  val tryPop: Reagent[Unit,Option[A]] = elimPop.map(Some(_)) <+> stack.tryPop
  val pop: Reagent[Unit,A] = elimPop <+> stack.pop
*/
}
