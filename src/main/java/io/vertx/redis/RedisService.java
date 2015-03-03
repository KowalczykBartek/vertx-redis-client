package io.vertx.redis;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.ProxyIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.impl.RedisServiceImpl;
import io.vertx.redis.op.AggregateOptions;
import io.vertx.redis.op.BitOperation;
import io.vertx.redis.op.InsertOptions;
import io.vertx.redis.op.KillFilter;
import io.vertx.redis.op.LimitOptions;
import io.vertx.redis.op.MigrateOptions;
import io.vertx.redis.op.ObjectCmd;
import io.vertx.redis.op.RangeLimitOptions;
import io.vertx.redis.op.RangeOptions;
import io.vertx.redis.op.ScanOptions;
import io.vertx.redis.op.SetOptions;
import io.vertx.redis.op.ShutdownOptions;
import io.vertx.redis.op.SortOptions;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;
import java.util.Map;

@VertxGen
@ProxyGen
public interface RedisService {

  static RedisService create(Vertx vertx, JsonObject config) {
    return new RedisServiceImpl(vertx, config);
  }

  static RedisService createEventBusProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(RedisService.class, vertx, address);
  }

  @ProxyIgnore
  void start(Handler<AsyncResult<Void>> handler);

  @ProxyIgnore
  void stop(Handler<AsyncResult<Void>> handler);

  /**
   * Append a value to a key
   *
   * @param key     Key string
   * @param value   Value to append
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: string
   */
  void append(String key, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Authenticate to the server
   *
   * @param password Password for authentication
   * @param handler  Handler for the result of this call.
   * @since 1.0.0
   * group: connection
   */
  void auth(String password, Handler<AsyncResult<String>> handler);

  /**
   * Asynchronously rewrite the append-only file
   *
   * @since 1.0.0
   * group: server
   */
  void bgrewriteaof(Handler<AsyncResult<String>> handler);

  /**
   * Asynchronously save the dataset to disk
   *
   * @since 1.0.0
   * group: server
   */
  void bgsave(Handler<AsyncResult<String>> handler);

  /**
   * Count set bits in a string
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: string
   */
  void bitcount(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Count set bits in a string
   *
   * @param key     Key string
   * @param start   Start index
   * @param end     End index
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: string
   */
  void bitcountRange(String key, long start, long end, Handler<AsyncResult<Long>> handler);

  /**
   * Perform bitwise operations between strings
   *
   * @param operation Bitwise operation to perform
   * @param destkey   Destination key where result is stored
   * @param keys      List of keys on which to perform the operation
   * @param handler   Handler for the result of this call.
   * @since 2.6.0
   * group: string
   */
  void bitop(BitOperation operation, String destkey, List<String> keys, Handler<AsyncResult<Long>> handler);

  /**
   * Find first bit set or clear in a string
   *
   * @param key     Key string
   * @param bit     What bit value to look for - must be 1, or 0
   * @param handler Handler for the result of this call.
   * @since 2.8.7
   * group: string
   */
  void bitpos(String key, int bit, Handler<AsyncResult<Long>> handler);

  /**
   * Find first bit set or clear in a string
   *
   * See also bitposRange() method, which takes start, and stop offset.
   *
   * @param key     Key string
   * @param bit     What bit value to look for - must be 1, or 0
   * @param start   Start offset
   * @param handler Handler for the result of this call.
   * @since 2.8.7
   * group: string
   */
  void bitposFrom(String key, int bit, int start, Handler<AsyncResult<Long>> handler);

  /**
   * Find first bit set or clear in a string
   *
   * Note: when both start, and stop offsets are specified,
   * behaviour is slightly different than if only start is specified
   *
   * @param key     Key string
   * @param bit     What bit value to look for - must be 1, or 0
   * @param start   Start offset
   * @param stop    End offset - inclusive
   * @param handler Handler for the result of this call.
   * @since 2.8.7
   * group: string
   */
  void bitposRange(String key, int bit, int start, int stop, Handler<AsyncResult<Long>> handler);

  /**
   * Remove and get the first element in a list, or block until one is available
   *
   * @param key     Key string identifying a list to watch
   * @param seconds Timeout in seconds
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: list
   */
  void blpop(String key, int seconds, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove and get the first element in any of the lists, or block until one is available
   *
   * @param keys    List of key strings identifying lists to watch
   * @param seconds Timeout in seconds
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: list
   */
  void blpopMany(List<String> keys, int seconds, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove and get the last element in a list, or block until one is available
   *
   * @param key     Key string identifying a list to watch
   * @param seconds Timeout in seconds
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: list
   */
  void brpop(String key, int seconds, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove and get the last element in any of the lists, or block until one is available
   *
   * @param keys    List of key strings identifying lists to watch
   * @param seconds Timeout in seconds
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: list
   */
  void brpopMany(List<String> keys, int seconds, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Pop a value from a list, push it to another list and return it; or block until one is available
   *
   * @param key     Key string identifying the source list
   * @param destkey Key string identifying the destination list
   * @param seconds Timeout in seconds
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: list
   */
  void brpoplpush(String key, String destkey, int seconds, Handler<AsyncResult<Void>> handler);

  /**
   * Kill the connection of a client
   *
   * @param filter  Filter options
   * @param handler Handler for the result of this call.
   * @since 2.4.0
   * group: server
   */
  void clientKill(KillFilter filter, Handler<AsyncResult<Long>> handler);

  /**
   * Get the list of client connections
   *
   * @since 2.4.0
   * group: server
   */
  void clientList(Handler<AsyncResult<String>> handler);

  /**
   * Get the current connection name
   *
   * @since 2.6.9
   * group: server
   */
  void clientGetname(Handler<AsyncResult<String>> handler);

  /**
   * Stop processing commands from clients for some time
   *
   * @param millis  Pause time in milliseconds
   * @param handler Handler for the result of this call.
   * @since 2.9.50
   * group: server
   */
  void clientPause(long millis, Handler<AsyncResult<String>> handler);

  /**
   * Set the current connection name
   *
   * @param name    New name for current connection
   * @param handler Handler for the result of this call.
   * @since 2.6.9
   * group: server
   */
  void clientSetname(String name, Handler<AsyncResult<String>> handler);

  /**
   * Get array of Cluster slot to node mappings
   *
   * @since 3.0.0
   * group: server
   */
  void clusterSlots(Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get array of Redis command details
   *
   * @since 2.8.13
   * group: server
   */
  void command(Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get total number of Redis commands
   *
   * @since 2.8.13
   * group: server
   */
  void commandCount(Handler<AsyncResult<Long>> handler);

  /**
   * Extract keys given a full Redis command
   *
   * @since 2.8.13
   * group: server
   */
  void commandGetkeys(Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get array of specific Redis command details
   *
   * @param commands List of commands to get info for
   * @param handler  Handler for the result of this call.
   * @since 2.8.13
   * group: server
   */
  void commandInfo(List<String> commands, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get the value of a configuration parameter
   *
   * @param parameter Configuration parameter
   * @param handler   Handler for the result of this call.
   * @since 2.0.0
   * group: server
   */
  void configGet(String parameter, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Rewrite the configuration file with the in memory configuration
   *
   * @since 2.8.0
   * group: server
   */
  void configRewrite(Handler<AsyncResult<String>> handler);

  /**
   * Set a configuration parameter to the given value
   *
   * @param parameter Configuration parameter
   * @param value     New value
   * @param handler   Handler for the result of this call.
   * @since 2.0.0
   * group: server
   */
  void configSet(String parameter, String value, Handler<AsyncResult<String>> handler);

  /**
   * Reset the stats returned by INFO
   *
   * @since 2.0.0
   * group: server
   */
  void configResetstat(Handler<AsyncResult<String>> handler);

  /**
   * Return the number of keys in the selected database
   *
   * @since 1.0.0
   * group: server
   */
  void dbsize(Handler<AsyncResult<Long>> handler);

  /**
   * Get debugging information about a key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: server
   */
  void debugObject(String key, Handler<AsyncResult<String>> handler);

  /**
   * Make the server crash
   *
   * @since 1.0.0
   * group: server
   */
  void debugSegfault(Handler<AsyncResult<String>> handler);

  /**
   * Decrement the integer value of a key by one
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void decr(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Decrement the integer value of a key by the given number
   *
   * @param key       Key string
   * @param decrement Value by which to decrement
   * @param handler   Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void decrby(String key, long decrement, Handler<AsyncResult<Long>> handler);

  /**
   * Delete a key
   *
   * @param keys    List of keys to delete
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void del(List<String> keys, Handler<AsyncResult<Long>> handler);

  /**
   * Discard all commands issued after MULTI
   *
   * @since 2.0.0
   * group: transactions
   */
  void discard(Handler<AsyncResult<String>> handler);

  /**
   * Return a serialized version of the value stored at the specified key.
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: generic
   */
  void dump(String key, Handler<AsyncResult<String>> handler);

  /**
   * Echo the given string
   *
   * @param message String to echo
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: connection
   */
  void echo(String message, Handler<AsyncResult<String>> handler);

  /**
   * Execute a Lua script server side
   *
   * @param script  Lua script to evaluate
   * @param keys    List of keys
   * @param args    List of argument values
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: scripting
   */
  void eval(String script, List<String> keys, List<String> args, Handler<AsyncResult<Void>> handler);

  /**
   * Execute a Lua script server side
   *
   * @param sha1    SHA1 digest of the script cached on the server
   * @param keys    List of keys
   * @param values  List of values
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: scripting
   */
  void evalsha(String sha1, List<String> keys, List<String> values, Handler<AsyncResult<Void>> handler);

  /**
   * Execute all commands issued after MULTI
   *
   * @since 1.2.0
   * group: transactions
   */
  void exec(Handler<AsyncResult<Void>> handler);

  /**
   * Determine if a key exists
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void exists(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Set a key's time to live in seconds
   *
   * @param key     Key string
   * @param seconds Time to live in seconds
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void expire(String key, int seconds, Handler<AsyncResult<Long>> handler);

  /**
   * Set the expiration for a key as a UNIX timestamp
   *
   * @param key       Key string
   * @param seconds   Expiry time as Unix timestamp in seconds
   * @param handler   Handler for the result of this call.
   * @since 1.2.0
   * group: generic
   */
  void expireat(String key, long seconds, Handler<AsyncResult<Long>> handler);

  /**
   * Remove all keys from all databases
   *
   * @since 1.0.0
   * group: server
   */
  void flushall(Handler<AsyncResult<String>> handler);

  /**
   * Remove all keys from the current database
   *
   * @since 1.0.0
   * group: server
   */
  void flushdb(Handler<AsyncResult<String>> handler);

  /**
   * Get the value of a key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void get(String key, Handler<AsyncResult<String>> handler);

  /**
   * Get the value of a key - without decoding as utf-8
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void getBinary(String key, Handler<AsyncResult<String>> handler);

  /**
   * Returns the bit value at offset in the string value stored at key
   *
   * @param key     Key string
   * @param offset  Offset in bits
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: string
   */
  void getbit(String key, long offset, Handler<AsyncResult<Long>> handler);

  /**
   * Get a substring of the string stored at a key
   *
   * @param key     Key string
   * @param start   Start offset
   * @param end     End offset - inclusive
   * @param handler Handler for the result of this call.
   * @since 2.4.0
   * group: string
   */
  void getrange(String key, long start, long end, Handler<AsyncResult<String>> handler);

  /**
   * Set the string value of a key and return its old value
   *
   * @param key     Key of which value to set
   * @param value   New value for the key
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void getset(String key, String value, Handler<AsyncResult<String>> handler);

  /**
   * Delete one or more hash fields
   *
   * @param key     Key string
   * @param field   Field name
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hdel(String key, String field, Handler<AsyncResult<Long>> handler);

  /**
   * Delete one or more hash fields
   *
   * @param key     Key string
   * @param fields  Field names
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hdelMany(String key, List<String> fields, Handler<AsyncResult<Long>> handler);

  /**
   * Determine if a hash field exists
   *
   * @param key     Key string
   * @param field   Field name
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hexists(String key, String field, Handler<AsyncResult<Long>> handler);

  /**
   * Get the value of a hash field
   *
   * @param key     Key string
   * @param field   Field name
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hget(String key, String field, Handler<AsyncResult<String>> handler);

  /**
   * Get all the fields and values in a hash
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hgetall(String key, Handler<AsyncResult<JsonObject>> handler);

  /**
   * Increment the integer value of a hash field by the given number
   *
   * @param key       Key string
   * @param field     Field name
   * @param increment Value by which to increment
   * @param handler   Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hincrby(String key, String field, long increment, Handler<AsyncResult<Long>> handler);

  /**
   * Increment the float value of a hash field by the given amount
   *
   * @param key       Key string
   * @param field     Field name
   * @param increment Value by which to increment
   * @param handler   Handler for the result of this call.
   * @since 2.6.0
   * group: hash
   */
  void hincrbyfloat(String key, String field, double increment, Handler<AsyncResult<String>> handler);

  /**
   * Get all the fields in a hash
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hkeys(String key, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get the number of fields in a hash
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hlen(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Get the values of all the given hash fields
   *
   * @param key     Key string
   * @param fields  Field names
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hmget(String key, List<String> fields, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Set multiple hash fields to multiple values
   *
   * @param key     Key string
   * @param values  Map of field:value pairs
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hmset(String key, Map<String, String> values, Handler<AsyncResult<String>> handler);

  /**
   * Set the string value of a hash field
   *
   * @param key     Key string
   * @param field   Field name
   * @param value   New value
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hset(String key, String field, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Set the value of a hash field, only if the field does not exist
   *
   * @param key     Key string
   * @param field   Field name
   * @param value   New value
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hsetnx(String key, String field, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Get all the values in a hash
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: hash
   */
  void hvals(String key, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Increment the integer value of a key by one
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void incr(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Increment the integer value of a key by the given amount
   *
   * @param key       Key string
   * @param increment Value by which to increment
   * @param handler   Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void incrby(String key, long increment, Handler<AsyncResult<Long>> handler);

  /**
   * Increment the float value of a key by the given amount
   *
   * @param key       Key string
   * @param increment Value by which to increment
   * @param handler   Handler for the result of this call.
   * @since 2.6.0
   * group: string
   */
  void incrbyfloat(String key, double increment, Handler<AsyncResult<String>> handler);

  /**
   * Get information and statistics about the server
   *
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: server
   */
  void info(Handler<AsyncResult<JsonObject>> handler);

  /**
   * Get information and statistics about the server
   *
   * @param section Specific section of information to return
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: server
   */
  void infoSection(String section, Handler<AsyncResult<JsonObject>> handler);

  /**
   * Find all keys matching the given pattern
   *
   * @param pattern Pattern to limit the keys returned
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void keys(String pattern, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get the UNIX time stamp of the last successful save to disk
   *
   * @since 1.0.0
   * group: server
   */
  void lastsave(Handler<AsyncResult<Long>> handler);

  /**
   * Get an element from a list by its index
   *
   * @param key     Key string
   * @param index   Index of list element to get
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lindex(String key, int index, Handler<AsyncResult<String>> handler);

  /**
   * Insert an element before or after another element in a list
   *
   * @param key     Key string
   * @param option  BEFORE or AFTER
   * @param pivot   Key to use as a pivot
   * @param value   Value to be inserted before or after the pivot
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: list
   */
  void linsert(String key, InsertOptions option, String pivot, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Get the length of a list
   *
   * @param key     String key
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void llen(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Remove and get the first element in a list
   *
   * @param key     String key
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lpop(String key, Handler<AsyncResult<String>> handler);

  /**
   * Prepend one or multiple values to a list
   *
   * @param key     Key string
   * @param values  Values to be added at the beginning of the list, one by one
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lpushMany(String key, List<String> values, Handler<AsyncResult<Long>> handler);

  /**
   * Prepend one value to a list
   *
   * @param key     Key string
   * @param value   Value to be added at the beginning of the list
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lpush(String key, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Prepend a value to a list, only if the list exists
   *
   * @param key     Key string
   * @param value   Value to add at the beginning of the list
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: list
   */
  void lpushx(String key, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Get a range of elements from a list
   *
   * @param key     Key string
   * @param from    Start index
   * @param to      Stop index
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lrange(String key, long from, long to, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove elements from a list
   *
   * @param key     Key string
   * @param count   Number of first found occurrences equal to $value to remove from the list
   * @param value   Value to be removed
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lrem(String key, long count, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Set the value of an element in a list by its index
   *
   * @param key     Key string
   * @param index   Position within list
   * @param value   New value
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void lset(String key, long index, String value, Handler<AsyncResult<String>> handler);

  /**
   * Trim a list to the specified range
   *
   * @param key     Key string
   * @param from    Start index
   * @param to      Stop index
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void ltrim(String key, long from, long to, Handler<AsyncResult<String>> handler);

  /**
   * Get the value of the given key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void mget(String key, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get the values of all the given keys
   *
   * @param keys    List of keys to get
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void mgetMany(List<String> keys, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Atomically transfer a key from a Redis instance to another one.
   *
   * @param host    Destination host
   * @param port    Destination port
   * @param key     Key to migrate
   * @param destdb  Destination database index
   * @param options Migrate options
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: generic
   */
  void migrate(String host, int port, String key, int destdb, long timeout, MigrateOptions options, Handler<AsyncResult<String>> handler);

  /**
   * Listen for all requests received by the server in real time
   *
   * @since 1.0.0
   * group: server
   */
  void monitor(Handler<AsyncResult<Void>> handler);

  /**
   * Move a key to another database
   *
   * @param key     Key to migrate
   * @param destdb  Destination database index
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void move(String key, int destdb, Handler<AsyncResult<Long>> handler);

  /**
   * Set multiple keys to multiple values
   *
   * @param keyvals Key value pairs to set
   * @param handler Handler for the result of this call.
   * @since 1.0.1
   * group: string
   */
  void mset(Map<String, String> keyvals, Handler<AsyncResult<String>> handler);

  /**
   * Set multiple keys to multiple values, only if none of the keys exist
   *
   * @param keyvals Key value pairs to set
   * @param handler Handler for the result of this call.
   * @since 1.0.1
   * group: string
   */
  void msetnx(Map<String, String> keyvals, Handler<AsyncResult<Long>> handler);

  /**
   * Mark the start of a transaction block
   *
   * @since 1.2.0
   * group: transactions
   */
  void multi(Handler<AsyncResult<String>> handler);

  /**
   * Inspect the internals of Redis objects
   *
   * @param key     Key string
   * @param cmd     Object sub command
   * @param handler Handler for the result of this call.
   * @since 2.2.3
   * group: generic
   */
  void object(String key, ObjectCmd cmd, Handler<AsyncResult<Void>> handler);

  /**
   * Remove the expiration from a key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: generic
   */
  void persist(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Set a key's time to live in milliseconds
   *
   * @param key     String key
   * @param millis  Time to live in milliseconds
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: generic
   */
  void pexpire(String key, long millis, Handler<AsyncResult<Long>> handler);

  /**
   * Set the expiration for a key as a UNIX timestamp specified in milliseconds
   *
   * @param key     Key string
   * @param millis  Expiry time as Unix timestamp in milliseconds
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: generic
   */
  void pexpireat(String key, long millis, Handler<AsyncResult<Long>> handler);

  /**
   * Adds the specified element to the specified HyperLogLog.
   *
   * @param key     Key string
   * @param element Element to add
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: hyperloglog
   */
  void pfadd(String key, String element, Handler<AsyncResult<Long>> handler);

  /**
   * Adds the specified elements to the specified HyperLogLog.
   *
   * @param key      Key string
   * @param elements Elementa to add
   * @param handler  Handler for the result of this call.
   * @since 2.8.9
   * group: hyperloglog
   */
  void pfaddMany(String key, List<String> elements, Handler<AsyncResult<Long>> handler);

  /**
   * Return the approximated cardinality of the set observed by the HyperLogLog at key.
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: hyperloglog
   */
  void pfcount(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Return the approximated cardinality of the set(s) observed by the HyperLogLog at key(s).
   *
   * @param keys    List of keys
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: hyperloglog
   */
  void pfcountMany(List<String> keys, Handler<AsyncResult<Long>> handler);

  /**
   * Merge N different HyperLogLogs into a single one.
   *
   * @param destkey Destination key
   * @param keys    List of source keys
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: hyperloglog
   */
  void pfmerge(String destkey, List<String> keys, Handler<AsyncResult<String>> handler);

  /**
   * Ping the server
   *
   * @since 1.0.0
   * group: connection
   */
  void ping(Handler<AsyncResult<String>> handler);

  /**
   * Set the value and expiration in milliseconds of a key
   *
   * @param key     Key string
   * @param millis  Number of milliseconds until the key expires
   * @param value   New value for key
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: string
   */
  void psetex(String key, long millis, String value, Handler<AsyncResult<Void>> handler);

  /**
   * Listen for messages published to channels matching the given pattern
   *
   * @param pattern Pattern string
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: pubsub
   */
  void psubscribe(String pattern, Handler<AsyncResult<Void>> handler);

  /**
   * Listen for messages published to channels matching the given patterns
   *
   * @param patterns List of patterns
   * @param handler  Handler for the result of this call.
   * @since 2.0.0
   * group: pubsub
   */
  void psubscribeMany(List<String> patterns, Handler<AsyncResult<Void>> handler);

  /**
   * Lists the currently active channels - only those matching the pattern
   *
   * @param pattern A glob-style pattern - an empty string means no pattern
   * @param handler Handler for the result of this call.
   * @since 2.8.0
   * group: pubsub
   */
  void pubsubChannels(String pattern, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Returns the number of subscribers (not counting clients subscribed to patterns) for the specified channels
   *
   * @param channels List of channels
   * @param handler  Handler for the result of this call.
   * @since 2.8.0
   * group: pubsub
   */
  void pubsubNumsub(List<String> channels, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Returns the number of subscriptions to patterns (that are performed using the PSUBSCRIBE command)
   *
   * @param handler Handler for the result of this call.
   * @since 2.8.0
   * group: pubsub
   */
  void pubsubNumpat(Handler<AsyncResult<Long>> handler);

  /**
   * Get the time to live for a key in milliseconds
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: generic
   */
  void pttl(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Post a message to a channel
   *
   * @param channel Channel key
   * @param message Message to send to channel
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: pubsub
   */
  void publish(String channel, String message, Handler<AsyncResult<Long>> handler);

  /**
   * Stop listening for messages posted to channels matching the given patterns
   *
   * @param patterns List of patterns to match against
   * @param handler  Handler for the result of this call.
   * @since 2.0.0
   * group: pubsub
   */
  void punsubscribe(List<String> patterns, Handler<AsyncResult<Void>> handler);

  /**
   * Close the connection
   *
   * @since 1.0.0
   * group: connection
   */
  void quit(Handler<AsyncResult<String>> handler);

  /**
   * Return a random key from the keyspace
   *
   * @since 1.0.0
   * group: generic
   */
  void randomkey(Handler<AsyncResult<String>> handler);

  /**
   * Rename a key
   *
   * @param key  Key string to be renamed
   * @param newkey  New key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void rename(String key, String newkey, Handler<AsyncResult<String>> handler);

  /**
   * Rename a key, only if the new key does not exist
   *
   * @param key  Key string to be renamed
   * @param newkey  New key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void renamenx(String key, String newkey, Handler<AsyncResult<Long>> handler);

  /**
   * Create a key using the provided serialized value, previously obtained using DUMP.
   *
   * @param key        Key string
   * @param millis     Expiry time in milliseconds to set on the key
   * @param serialized Serialized form of the key value as obtained using DUMP
   * @param handler    Handler for the result of this call.
   * @since 2.6.0
   * group: generic
   */
  void restore(String key, long millis, String serialized, Handler<AsyncResult<String>> handler);

  /**
   * Return the role of the instance in the context of replication
   *
   * @since 2.8.12
   * group: server
   */
  void role(Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove and get the last element in a list
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void rpop(String key, Handler<AsyncResult<String>> handler);

  /**
   * Remove the last element in a list, append it to another list and return it
   *
   * @param key     Key string identifying source list
   * @param destkey Key string identifying destination list
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: list
   */
  void rpoplpush(String key, String destkey, Handler<AsyncResult<String>> handler);

  /**
   * Append one or multiple values to a list
   *
   * @param key     Key string
   * @param values  List of values to add to the end of the list
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void rpushMany(String key, List<String> values, Handler<AsyncResult<Long>> handler);

  /**
   * Append one or multiple values to a list
   *
   * @param key     Key string
   * @param value   Value to be added to the end of the list
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: list
   */
  void rpush(String key, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Append a value to a list, only if the list exists
   *
   * @param key     Key string
   * @param value   Value to be added to the end of the list
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: list
   */
  void rpushx(String key, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Add a member to a set
   *
   * @param key     Key string
   * @param member  Value to be added to the set
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sadd(String key, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Add one or more members to a set
   *
   * @param key     Key string
   * @param members Values to be added to the set
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void saddMany(String key, List<String> members, Handler<AsyncResult<Long>> handler);

  /**
   * Synchronously save the dataset to disk
   *
   * @since 1.0.0
   * group: server
   */
  void save(Handler<AsyncResult<String>> handler);

  /**
   * Get the number of members in a set
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void scard(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Check existence of script in the script cache.
   *
   * @param script  SHA1 digest identifying a script in the script cache
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: scripting
   */
  void scriptExists(String script, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Check existence of scripts in the script cache.
   *
   * @param scripts List of SHA1 digests identifying scripts in the script cache
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: scripting
   */
  void scriptExistsMany(List<String> scripts, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove all the scripts from the script cache.
   *
   * @since 2.6.0
   * group: scripting
   */
  void scriptFlush(Handler<AsyncResult<String>> handler);

  /**
   * Kill the script currently in execution.
   *
   * @since 2.6.0
   * group: scripting
   */
  void scriptKill(Handler<AsyncResult<String>> handler);

  /**
   * Load the specified Lua script into the script cache.
   *
   * @param script  Lua script
   * @param handler Handler for the result of this call.
   * @since 2.6.0
   * group: scripting
   */
  void scriptLoad(String script, Handler<AsyncResult<String>> handler);

  /**
   * Subtract multiple sets
   *
   * @param key     Key identifying the set to compare with all other sets combined
   * @param cmpkeys List of keys identifying sets to subtract from the key set
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sdiff(String key, List<String> cmpkeys, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Subtract multiple sets and store the resulting set in a key
   *
   * @param destkey Destination key where the result should be stored
   * @param key     Key identifying the set to compare with all other sets combined
   * @param cmpkeys List of keys identifying sets to subtract from the key set
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sdiffstore(String destkey, String key, List<String> cmpkeys, Handler<AsyncResult<Long>> handler);

  /**
   * Change the selected database for the current connection
   *
   * @param dbindex Index identifying the new active database
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: connection
   */
  void select(int dbindex, Handler<AsyncResult<String>> handler);

  /**
   * Set the string value of a key
   *
   * @param key     Key of which value to set
   * @param value   New value for the key
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void set(String key, String value, Handler<AsyncResult<Void>> handler);

  /**
   * Set the string value of a key
   *
   * @param key     Key of which value to set
   * @param value   New value for the key
   * @param options Set options
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void setWithOptions(String key, String value, SetOptions options, Handler<AsyncResult<Void>> handler);

  /**
   * Set the binary string value of a key - without encoding as utf-8
   *
   * @param key     Key of which value to set
   * @param value   New value for the key
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void setBinary(String key, String value, Handler<AsyncResult<Void>> handler);

  /**
   * Sets or clears the bit at offset in the string value stored at key
   *
   * @param key     Key string
   * @param offset  Bit offset
   * @param bit     New value - must be 1 or 0
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: string
   */
  void setbit(String key, long offset, int bit, Handler<AsyncResult<Long>> handler);

  /**
   * Set the value and expiration of a key
   *
   * @param key     Key string
   * @param seconds Number of seconds until the key expires
   * @param value   New value for key
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: string
   */
  void setex(String key, long seconds, String value, Handler<AsyncResult<String>> handler);

  /**
   * Set the value of a key, only if the key does not exist
   *
   * @param key     Key of which value to set
   * @param value   New value for the key
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: string
   */
  void setnx(String key, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Overwrite part of a string at key starting at the specified offset
   *
   * @param key     Key string
   * @param offset  Offset - the maximum offset that you can set is 2^29 -1 (536870911), as Redis Strings are limited to 512 megabytes
   * @param value   Value to overwrite with
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: string
   */
  void setrange(String key, int offset, String value, Handler<AsyncResult<Long>> handler);

  /**
   * Synchronously save the dataset to disk and then shut down the server
   *
   * @param options Shutdown options
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: server
   */
  void shutdown(ShutdownOptions options, Handler<AsyncResult<String>> handler);

  /**
   * Intersect multiple sets
   *
   * @param keys    List of keys to perform intersection on
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sinter(List<String> keys, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Intersect multiple sets and store the resulting set in a key
   *
   * @param destkey Key where to store the results
   * @param keys    List of keys to perform intersection on
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sinterstore(String destkey, List<String> keys, Handler<AsyncResult<Long>> handler);

  /**
   * Determine if a given value is a member of a set
   *
   * @param key     Key string
   * @param member  Member to look for
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sismember(String key, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Make the server a slave of another instance
   *
   * @param host    Host to become this server's master
   * @param port    Port of our new master
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: server
   */
  void slaveof(String host, int port, Handler<AsyncResult<String>> handler);

  /**
   * Make this server a master
   *
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: server
   */
  void slaveofNoone(Handler<AsyncResult<String>> handler);

  /**
   * Read the Redis slow queries log
   *
   * @param limit   Number of log entries to return. If value is less than zero all entries are returned
   * @param handler Handler for the result of this call.
   * @since 2.2.12
   * group: server
   */
  void slowlogGet(int limit, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get the length of the Redis slow queries log
   *
   * @param handler Handler for the result of this call.
   * @since 2.2.12
   * group: server
   */
  void slowlogLen(Handler<AsyncResult<Long>> handler);

  /**
   * Reset the Redis slow queries log
   *
   * @param handler Handler for the result of this call.
   * @since 2.2.12
   * group: server
   */
  void slowlogReset(Handler<AsyncResult<Void>> handler);

  /**
   * Get all the members in a set
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void smembers(String key, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Move a member from one set to another
   *
   * @param key     Key of source set currently containing the member
   * @param destkey Key identifying the destination set
   * @param member   Member to move
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void smove(String key, String destkey, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Sort the elements in a list, set or sorted set
   *
   * @param key     Key string
   * @param options Sort options
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void sort(String key, SortOptions options, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove and return a random member from a set
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void spop(String key, Handler<AsyncResult<String>> handler);

  /**
   * Remove and return random members from a set
   *
   * @param key     Key string
   * @param count   Number of members to remove
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void spopMany(String key, int count, Handler<AsyncResult<String>> handler);

  /**
   * Get one or multiple random members from a set
   *
   * @param key     Key string
   * @param count   Number of members to get
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void srandmember(String key, int count, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Remove one member from a set
   *
   * @param key     Key string
   * @param member  Member to remove
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void srem(String key, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Remove one or more members from a set
   *
   * @param key     Key string
   * @param members Members to remove
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sremMany(String key, List<String> members, Handler<AsyncResult<Long>> handler);

  /**
   * Get the length of the value stored in a key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: string
   */
  void strlen(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Listen for messages published to the given channels
   *
   * @param channels List of channels to subscribe to
   * @param handler  Handler for the result of this call.
   * @since 2.0.0
   * group: pubsub
   */
  void subscribe(List<String> channels, Handler<AsyncResult<Void>> handler);

  /**
   * Add multiple sets
   *
   * @param keys    List of keys identifying sets to add up
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sunion(List<String> keys, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Add multiple sets and store the resulting set in a key
   *
   * @param destkey Destination key
   * @param keys    List of keys identifying sets to add up
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: set
   */
  void sunionstore(String destkey, List<String> keys, Handler<AsyncResult<Long>> handler);

  /**
   * Internal command used for replication
   *
   * @since 1.0.0
   * group: server
   */
  void sync(Handler<AsyncResult<Void>> handler);

  /**
   * Return the current server time
   *
   * @since 2.6.0
   * group: server
   */
  void time(Handler<AsyncResult<JsonArray>> handler);

  /**
   * Get the time to live for a key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void ttl(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Determine the type stored at key
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.0.0
   * group: generic
   */
  void type(String key, Handler<AsyncResult<String>> handler);

  /**
   * Stop listening for messages posted to the given channels
   *
   * @param channels List of channels to subscribe to
   * @param handler  Handler for the result of this call.
   * @since 2.0.0
   * group: pubsub
   */
  void unsubscribe(List<String> channels, Handler<AsyncResult<Void>> handler);

  /**
   * Forget about all watched keys
   *
   * @since 2.2.0
   * group: transactions
   */
  void unwatch(Handler<AsyncResult<String>> handler);

  /**
   * Watch the given keys to determine execution of the MULTI/EXEC block
   *
   * @param keys    List of keys to watch
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: transactions
   */
  void watch(List<String> keys, Handler<AsyncResult<String>> handler);

  /**
   * Add one or more members to a sorted set, or update its score if it already exists
   *
   * @param key     Key string
   * @param score   Score used for sorting
   * @param member  New member key
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zadd(String key, double score, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Add one or more members to a sorted set, or update its score if it already exists
   *
   * @param key     Key string
   * @param members New member keys and their scores
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zaddMany(String key, Map<String, Double> members, Handler<AsyncResult<Long>> handler);

  /**
   * Get the number of members in a sorted set
   *
   * @param key     Key string
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zcard(String key, Handler<AsyncResult<Long>> handler);

  /**
   * Count the members in a sorted set with scores within the given values
   *
   * @param key     Key string
   * @param min     Minimum score
   * @param max     Maximum score
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zcount(String key, double min, double max, Handler<AsyncResult<Long>> handler);

  /**
   * Increment the score of a member in a sorted set
   *
   * @param key       Key string
   * @param increment Increment amount
   * @param member    Member key
   * @param handler   Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zincrby(String key, double increment, String member, Handler<AsyncResult<String>> handler);

  /**
   * Intersect multiple sorted sets and store the resulting sorted set in a new key
   *
   * @param destkey Destination key
   * @param sets    List of keys identifying sorted sets to intersect
   * @param options Aggregation options
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zinterstore(String destkey, List<String> sets, AggregateOptions options, Handler<AsyncResult<Long>> handler);

  /**
   * Intersect multiple sorted sets and store the resulting sorted set in a new key using weights for scoring
   *
   * @param destkey Destination key
   * @param sets    List of keys identifying sorted sets to intersect
   * @param options Aggregation options
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zinterstoreWeighed(String destkey, Map<String, Double> sets, AggregateOptions options, Handler<AsyncResult<Long>> handler);

  /**
   * Count the number of members in a sorted set between a given lexicographical range
   *
   * @param key     Key string
   * @param min     Pattern to compare against for minimum value
   * @param max     Pattern to compare against for maximum value
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: sorted_set
   */
  void zlexcount(String key, String min, String max, Handler<AsyncResult<Long>> handler);

  /**
   * Return a range of members in a sorted set, by index
   *
   * @param key     Key string
   * @param start   Start index for the range
   * @param stop    Stop index for the range - inclusive
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zrange(String key, long start, long stop, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Return a range of members in a sorted set, by index
   *
   * @param key     Key string
   * @param start   Start index for the range
   * @param stop    Stop index for the range - inclusive
   * @param options Range options
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zrangeWithOptions(String key, long start, long stop, RangeOptions options, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Return a range of members in a sorted set, by lexicographical range
   *
   * @param key     Key string
   * @param min     Pattern representing a minimum allowed value
   * @param max     Pattern representing a maximum allowed value
   * @param options Limit options where limit can be specified
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: sorted_set
   */
  void zrangebylex(String key, String min, String max, LimitOptions options, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Return a range of members in a sorted set, by score
   *
   * @param key     Key string
   * @param min     Pattern defining a minimum value
   * @param max     Pattern defining a maximum value
   * @param options Range and limit options
   * @param handler Handler for the result of this call.
   * @since 1.0.5
   * group: sorted_set
   */
  void zrangebyscore(String key, String min, String max, RangeLimitOptions options, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Determine the index of a member in a sorted set
   *
   * @param key     Key string
   * @param member  Member in the sorted set identified by key
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zrank(String key, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Remove one member from a sorted set
   *
   * @param key     Key string
   * @param member  Member in the sorted set identified by key
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zrem(String key, String member, Handler<AsyncResult<Long>> handler);

  /**
   * Remove one or more members from a sorted set
   *
   * @param key     Key string
   * @param members Members in the sorted set identified by key
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zremMany(String key, List<String> members, Handler<AsyncResult<Long>> handler);

  /**
   * Remove all members in a sorted set between the given lexicographical range
   *
   * @param key     Key string
   * @param min     Pattern defining a minimum value
   * @param max     Pattern defining a maximum value
   * @param handler Handler for the result of this call.
   * @since 2.8.9
   * group: sorted_set
   */
  void zremrangebylex(String key, String min, String max, Handler<AsyncResult<Long>> handler);

  /**
   * Remove all members in a sorted set within the given indexes
   *
   * @param key     Key string
   * @param start   Start index
   * @param stop    Stop index
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zremrangebyrank(String key, long start, long stop, Handler<AsyncResult<Long>> handler);

  /**
   * Remove all members in a sorted set within the given scores
   *
   * @param key     JsonArray [{"name":"key","type":"key"},{"name":"min","type":"double"},{"name":"max","type":"double"}]
   * @param min     Pattern defining a minimum value
   * @param max     Pattern defining a maximum value
   * @since 1.2.0
   * group: sorted_set
   */
  void zremrangebyscore(String key, String min, String max, Handler<AsyncResult<Long>> handler);

  /**
   * Return a range of members in a sorted set, by index, with scores ordered from high to low
   *
   * @param args    JsonArray [{"name":"key","type":"key"},{"name":"start","type":"integer"},{"name":"stop","type":"integer"},{"name":"withscores","type":"enum","enum":["WITHSCORES"],"optional":true}]
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zrevrange(JsonArray args, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Return a range of members in a sorted set, by score, with scores ordered from high to low
   *
   * @param args    JsonArray [{"name":"key","type":"key"},{"name":"max","type":"double"},{"name":"min","type":"double"},{"name":"withscores","type":"enum","enum":["WITHSCORES"],"optional":true},{"command":"LIMIT","name":["offset","count"],"type":["integer","integer"],"optional":true}]
   * @param handler Handler for the result of this call.
   * @since 2.2.0
   * group: sorted_set
   */
  void zrevrangebyscore(JsonArray args, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Determine the index of a member in a sorted set, with scores ordered from high to low
   *
   * @param args    JsonArray [{"name":"key","type":"key"},{"name":"member","type":"string"}]
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zrevrank(JsonArray args, Handler<AsyncResult<Long>> handler);

  /**
   * Get the score associated with the given member in a sorted set
   *
   * @param args    JsonArray [{"name":"key","type":"key"},{"name":"member","type":"string"}]
   * @param handler Handler for the result of this call.
   * @since 1.2.0
   * group: sorted_set
   */
  void zscore(JsonArray args, Handler<AsyncResult<String>> handler);

  /**
   * Add multiple sorted sets and store the resulting sorted set in a new key
   *
   * @param destkey Destination key
   * @param sets    List of keys identifying sorted sets
   * @param options Aggregation options
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zunionstore(String destkey, List<String> sets, AggregateOptions options, Handler<AsyncResult<Long>> handler);

  /**
   * Add multiple sorted sets using weights, and store the resulting sorted set in a new key
   *
   * @param key     Destination key
   * @param sets    Map containing set-key:weight pairs
   * @param options Aggregation options
   * @param handler Handler for the result of this call.
   * @since 2.0.0
   * group: sorted_set
   */
  void zunionstoreWeighed(String key, Map<String, Double> sets, AggregateOptions options, Handler<AsyncResult<Long>> handler);

  /**
   * Incrementally iterate the keys space
   *
   * @param args    JsonArray [{"name":"cursor","type":"integer"},{"command":"MATCH","name":"pattern","type":"pattern","optional":true},{"command":"COUNT","name":"count","type":"integer","optional":true}]
   * @param handler Handler for the result of this call.
   * @since 2.8.0
   * group: generic
   */
  void scan(JsonArray args, Handler<AsyncResult<Void>> handler);

  /**
   * Incrementally iterate Set elements
   *
   * @param key     Key string
   * @param cursor  Cursor id
   * @param options Scan options
   * @param handler Handler for the result of this call.
   * @since 2.8.0
   * group: set
   */
  void sscan(String key, String cursor, ScanOptions options, Handler<AsyncResult<Void>> handler);

  /**
   * Incrementally iterate hash fields and associated values
   *
   * @param key     Key string
   * @param cursor  Cursor id
   * @param options Scan options
   * @param handler Handler for the result of this call.
   * @since 2.8.0
   * group: hash
   */
  void hscan(String key, String cursor, ScanOptions options, Handler<AsyncResult<JsonArray>> handler);

  /**
   * Incrementally iterate sorted sets elements and associated scores
   *
   * @param args    JsonArray [{"name":"key","type":"key"},{"name":"cursor","type":"integer"},{"command":"MATCH","name":"pattern","type":"pattern","optional":true},{"command":"COUNT","name":"count","type":"integer","optional":true}]
   * @param handler Handler for the result of this call.
   * @since 2.8.0
   * group: sorted_set
   */
  void zscan(JsonArray args, Handler<AsyncResult<Void>> handler);

}
