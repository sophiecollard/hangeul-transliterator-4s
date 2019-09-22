package com.github.sophiecollard.hangeul4s.encoding

import com.github.sophiecollard.hangeul4s.error.EncodingError

trait Encoder[E, D] {

  def encode(decoded: D): Either[EncodingError, E]

}

object Encoder {

  def instance[E, D](f: D => Either[EncodingError, E]): Encoder[E, D] =
    new Encoder[E, D] {
      override def encode(decoded: D): Either[EncodingError, E] =
        f(decoded)
    }

}