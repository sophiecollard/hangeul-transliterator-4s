package com.github.sophiecollard.hangeul4s.util.typeclasses

trait Traverse[F[_]] {

  def traverse[G[_]: Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]

  def sequence[G[_]: Applicative, A](fga: F[G[A]]): G[F[A]] =
    traverse(fga)(identity)

}

object Traverse {

  def apply[F[_]](implicit ev: Traverse[F]): Traverse[F] =
    ev

  def traverse[F[_], G[_]: Applicative, A, B](fa: F[A])(f: A => G[B])(implicit ev: Traverse[F]): G[F[B]] =
    ev.traverse(fa)(f)

  def sequence[F[_], G[_]: Applicative, A](fga: F[G[A]])(implicit ev: Traverse[F]): G[F[A]] =
    ev.sequence(fga)

}
