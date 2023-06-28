# Caching questions and answers

Caching is used to serve stored data more efficiently. It is commonly used, particularly in large scale distributed systems, to allow the most frequently accessed information to be available at a lower cost in time or resources. Since cache capacity is limited, strategies are employed to determine what is in the cache and when it should be replaced. These are referred to as eviction policies and replacement policies. Which of the following are true?
Pick ONE OR MORE options
- A cache hit is a situation when the cache becomes full.
- LRU and MRU are eviction policies that respectively discard least recently used and most recently used elements from the cache to make space for other elements. In applications like social networks with feed, LRU, in general, performs significantly better than MRU.
- If the future is known, it is possible to design an eviction policy that is optimal.
- | The data in a cache is always consistent with the changes made to it. Any time it is requested, the most recent version is returned.


The correct statements are:

LRU and MRU are eviction policies that respectively discard least recently used and most recently used elements from the cache to make space for other elements. In applications like social networks with feed, LRU, in general, performs significantly better than MRU.

If the future is known, it is possible to design an eviction policy that is optimal.

The other statements are incorrect:

A cache hit refers to a situation when the requested data is found in the cache, and a cache miss refers to a situation when the requested data is not found in the cache. It does not necessarily imply that the cache becomes full.

The data in a cache may not always be consistent with the changes made to it. Caches operate based on a principle of providing fast access to frequently accessed data. Therefore, there may be a delay in updating the cached data when changes are made to the underlying data source. Caches employ various techniques, such as expiration times or invalidation mechanisms, to maintain data consistency between the cache and the data source.