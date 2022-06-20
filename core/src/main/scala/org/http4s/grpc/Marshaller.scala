/*
 * Copyright 2022 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.http4s.grpc

import fs2.Stream
import scodec.bits.ByteVector

trait Marshaller[F[_], A]:
  def marshall(a: A): F[Marshalled[F]]
  def unmarshall(m: Marshalled[F]): F[A]

sealed abstract class Marshalled[+F[_]]

object Marshalled:
  final case class Strict(bytes: ByteVector) extends Marshalled[Nothing]
  final case class Streamed[+F[_]](bytes: Stream[F, Byte]) extends Marshalled[F]
