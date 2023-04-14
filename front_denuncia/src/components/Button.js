import React from "react";
import { Text, StyleSheet, TouchableOpacity } from 'react-native';

import { useForm } from 'react-hook-form';



export function Button({ onPress }) {
    const { control, handleSubmit, formState: { erros } } = useForm({});
    return (
            <TouchableOpacity style={styles.button} onPress={handleSubmit(onPress)}>
                <Text style={styles.buttonText}>Next</Text>
            </TouchableOpacity>
    )
}


const styles = StyleSheet.create({
    button: {
        backgroundColor: "purple",
        padding: 20,
        alignItems: 'center',
        borderRadius: 7,
        marginTop: 20
    },
    buttonText: {
        color: '#fff',
        fontSize: 15,
        fontWeight: 'bold',
    }
})