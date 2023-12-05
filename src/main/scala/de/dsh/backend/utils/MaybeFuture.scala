package de.dsh.backend.utils

import scala.concurrent.*

type MaybeFuture[T] = Future[T] | T
