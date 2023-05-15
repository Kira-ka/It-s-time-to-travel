package ru.netology.itstimetotravel.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.itstimetotravel.dto.Plain

@Entity
data class PlainEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val from: String,
    val to: String,
    val departureDate: Long,
    val returnDate: Long,
    val price: Int,
    val likedByMe: Boolean
) {
    fun toDto() = Plain(id, from, to, departureDate, returnDate, price, likedByMe)

    companion object {
        fun fromDto(dto: Plain) = PlainEntity(
            dto.id,
            dto.from,
            dto.to,
            dto.departureDate,
            dto.returnDate,
            dto.price,
            dto.likedByMe
        )
    }

}
fun List<PlainEntity>.toDto(): List<Plain> = map(PlainEntity::toDto)
fun List<Plain>.toEntity(): List<PlainEntity> = map(PlainEntity::fromDto)
