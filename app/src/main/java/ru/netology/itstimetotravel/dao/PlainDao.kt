package ru.netology.itstimetotravel.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import  ru.netology.itstimetotravel.entity.PlainEntity

@Dao
interface PlainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plain: PlainEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plain: List<PlainEntity>)

    @Query("SELECT*FROM PlainEntity ORDER BY id DESC")
    fun getAll(): Flow<List<PlainEntity>>

    @Query(
        """
      UPDATE PlainEntity SET 
      likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
      WHERE id = :id
   """
    )
    fun likeById(id: Long)

}
