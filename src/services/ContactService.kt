package services

import entity.ContactEntity
import repository.ContactRepository

class ContactService {

    private fun validateSaveFields(name: String, phoneNumber: String) {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("Name cannot be empty.")
        }

        if(phoneNumber.isNullOrBlank()) {
            throw IllegalArgumentException("Phone number cannot be empty.")
        }
    }

    private fun validateDeleteFields(name: String, phoneNumber: String) {
        if(name.isNullOrBlank() || phoneNumber.isNullOrBlank()) {
            throw IllegalArgumentException("You need to select a contact to remove.")
        }
    }

    fun save(name: String, phoneNumber: String) {
        validateSaveFields(name, phoneNumber)

        val contact = ContactEntity(name, phoneNumber)
        ContactRepository.save(contact)
    }

    fun delete(name: String, phoneNumber: String) {
        validateDeleteFields(name, phoneNumber)

        val contact = ContactEntity(name, phoneNumber)
        ContactRepository.delete(contact)
    }

    fun getContactList(): List<ContactEntity> {
        return ContactRepository.getContactList()
    }

    fun getContactCountDescription(): String {
        val list = getContactList()

        return when {
            list.isNullOrEmpty() -> "0 Contacts"
            list.size == 1 -> "1 Contact"
            else -> "${list.size} Contacts"
        }
    }
}
